package com.samuelminana.tema4gradle;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.data.message.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final String BASE_URL = "http://localhost:11434/v1";
        final String TOKEN = "ollama"; // en local no importa

        var iaPregunta = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(TOKEN)
                .modelName("gemma:2b")
                .build();

        var iaRespuesta = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(TOKEN)
                .modelName("llama3.1:8b")
                .build();

        List<ChatMessage> historyA = new ArrayList<>();
        historyA.add(SystemMessage.from(
                "Eres una IA curiosa y haces preguntas claras sobre tecnología."
        ));

        historyA.add(UserMessage.from(
                "Haz UNA sola pregunta breve sobre inteligencia artificial."
        ));

        AiMessage pregunta = iaPregunta.chat(historyA).aiMessage();
        historyA.add(pregunta);

        List<ChatMessage> historyB = new ArrayList<>();
        historyB.add(SystemMessage.from(
                "Eres una IA experta que responde de forma clara y educativa."
        ));

        historyB.add(UserMessage.from(pregunta.text()));
        AiMessage respuesta = iaRespuesta.chat(historyB).aiMessage();
        historyB.add(respuesta);

        System.out.println("IA A (gemma:2b) pregunta:");
        System.out.println(pregunta.text());

        System.out.println("\nIA B (llama3.1:8b) responde:");
        System.out.println(respuesta.text());
    }
}
