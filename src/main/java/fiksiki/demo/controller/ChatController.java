package fiksiki.demo.controller;

import fiksiki.demo.services.JWTService;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class ChatController {

    private final OpenAiChatModel chatModel;
    private final JWTService jwtService;



    @Autowired
    public ChatController(OpenAiChatModel chatModel, JWTService jwtService) {
        this.chatModel = chatModel;
        this.jwtService = jwtService;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generate(@RequestBody String message,
                                   @RequestHeader("Authorization") String auth) {
        String token = null;
        String username = null;

        if (auth != null && auth.startsWith("Bearer ")) {
            token = auth.substring(7);
            username = jwtService.extractUserName(token);
            if (username != null) {
                String response = this.chatModel.call(message);
                return new ResponseEntity<>(response.substring(8, response.length()-3), HttpStatusCode.valueOf(200));
            } else {
                return new ResponseEntity<>("Invalid token", HttpStatusCode.valueOf(402));
            }

        }
        return new ResponseEntity<>("You'll never reach this message, lol!", HttpStatusCode.valueOf(401));

    }

    @GetMapping("/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return this.chatModel.stream(prompt);
    }
}
