package com.consultantbot.consultant.config;

import com.consultantbot.consultant.aiservice.ConsultantService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author jarvisdu
 * @description CommonConfig
 * @date 2025/7/29
 */

@Configuration
public class CommonConfig {
//    @Autowired
//    private OpenAiChatModel model;
//    @Bean
//    public ConsultantService consultantService() {
//        ConsultantService consultantService=AiServices.builder(ConsultantService.class)
//                .chatModel(model)
//                .build();
//        return consultantService;
//    }
    @Autowired
    private ChatMemoryStore redisChatMemoryStore;
    @Autowired
    private EmbeddingModel embeddingModel;

    // 构建会话记忆对象
    @Bean
    public ChatMemory chatMemory(){
        MessageWindowChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
        return memory;
    }

    // 构件ChatMemoryProvider对象
    @Bean
    public ChatMemoryProvider chatMemoryProvider(){
        ChatMemoryProvider chatMemoryProvider = new ChatMemoryProvider(){
            @Override
            public ChatMemory get(Object memoryId) {
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .maxMessages(20)
                        .chatMemoryStore(redisChatMemoryStore)
                        .build();
            }
        };
        return chatMemoryProvider;
    }

    // 构建向量数据库操作对象
    @Bean
    public EmbeddingStore store(){

        //加载文档进内存
        // List<Document> documents = ClassPathDocumentLoader.loadDocuments("content");
        // List<Document> documents = FileSystemDocumentLoader.loadDocuments("E:\\Ado's Legion Y7000P\\Code\\Plan\\Java\\Langchain4jProject\\ConsultantBot\\src\\main\\resources\\content");
        List<Document> documents = ClassPathDocumentLoader.loadDocuments("content",new ApachePdfBoxDocumentParser());
        // 构建向量数据库操作对象
        InMemoryEmbeddingStore store = new InMemoryEmbeddingStore();

        // 构建文档分割器对象
        DocumentSplitter splitter = DocumentSplitters.recursive(500,100);
        // 构建一个EmbeddingStoreIngestor对象, 完成文本数据切割、向量化、存储
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
                .documentSplitter(splitter)
                .embeddingModel(embeddingModel)
                .build();
        ingestor.ingest(documents);
        return store;
    }

    @Bean
    // 构建向量数据库检索对象
    public ContentRetriever contentRetriever(EmbeddingStore store){
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(store)
                .maxResults(3)
                .minScore(0.75)
                .embeddingModel(embeddingModel)
                .build();
    }
}
