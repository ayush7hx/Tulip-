package com.dishaspecial.portal.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class DishaPortalBackend {
    public static void main(String[] args) {
        SpringApplication.run(DishaPortalBackend.class, args);
    }
}

// Controller for handling requests
@RestController
@RequestMapping("/api/portal")
@CrossOrigin(origins = "*")
class DishPortalController {
    
    private static int visitorCount = 0;
    private static List<String> messages = new ArrayList<>();
    
    // Get visitor statistics
    @GetMapping("/stats")
    public PortalStats getStats() {
        return new PortalStats(visitorCount, messages.size(), LocalDateTime.now());
    }
    
    // Increment visitor count
    @PostMapping("/visit")
    public VisitResponse recordVisit() {
        visitorCount++;
        return new VisitResponse(visitorCount, "Welcome back! 💜");
    }
    
    // Get K-Drama recommendations
    @GetMapping("/kdrama-suggestions")
    public List<String> getKDramaSuggestions() {
        return Arrays.asList(
            "Itaewon Class - A tale of redemption and justice 🔥",
            "Descendants of the Sun - Romance and action combined 💕",
            "My Love from the Star - Classic romantic K-drama ✨",
            "Squid Game - Thrilling and unforgettable 🎭",
            "Business Proposal - Funny and heartwarming 😂",
            "Crash Landing on You - Adventure and love 🪂",
            "True Beauty - Slice of life with depth 💫"
        );
    }
    
    // Get personalized messages
    @GetMapping("/messages")
    public List<String> getMessages() {
        return Arrays.asList(
            "You're someone truly special 💫",
            "This project was made thinking of you ✨",
            "Keep being awesome, Sunshine ☀️",
            "From Karnataka to my heart 💜",
            "K-dramas could never capture your essence",
            "You make ordinary moments extraordinary",
            "Forever grateful for you 💕"
        );
    }
    
    // Get timeline events
    @GetMapping("/timeline")
    public List<TimelineEvent> getTimeline() {
        List<TimelineEvent> timeline = new ArrayList<>();
        timeline.add(new TimelineEvent("The Beginning", "When we first met and instantly clicked", "🌟"));
        timeline.add(new TimelineEvent("Growing Closer", "Discovering shared interests and inside jokes", "💫"));
        timeline.add(new TimelineEvent("Special Moments", "Creating memories that we'll cherish forever", "✨"));
        timeline.add(new TimelineEvent("Forever Bond", "Whatever we are, we're in this together", "💜"));
        return timeline;
    }
    
    // Submit quiz response
    @PostMapping("/quiz/submit")
    public QuizResult submitQuiz(@RequestBody QuizResponse response) {
        String result = generatePersonalityResult(response);
        return new QuizResult(result, "Your K-Drama Personality", true);
    }
    
    private String generatePersonalityResult(QuizResponse response) {
        String[] results = {
            "You're a romantic at heart! 💕 Perfect for K-drama marathons with someone special.",
            "You're an action-packed personality! 🔥 Always bringing excitement to the story.",
            "You're the life of the party! 😂 Making every moment fun and memorable.",
            "You're mysterious and intriguing! 🎬 There's always more to discover about you.",
            "You're an adventurous soul! 🗺️ Ready to explore new worlds and experiences.",
            "You're a homebody who values comfort! 🏠 Creating cozy moments that matter.",
            "You're someone special! 💫 Making ordinary moments extraordinary."
        };
        
        Random random = new Random();
        return results[random.nextInt(results.length)];
    }
    
    // Health check
    @GetMapping("/health")
    public HealthCheck getHealth() {
        return new HealthCheck("Portal is running perfectly! 💜", true);
    }
}

// Data Models
class PortalStats {
    public int visitors;
    public int messages;
    public LocalDateTime lastUpdated;
    
    public PortalStats(int visitors, int messages, LocalDateTime lastUpdated) {
        this.visitors = visitors;
        this.messages = messages;
        this.lastUpdated = lastUpdated;
    }
}

class VisitResponse {
    public int totalVisits;
    public String message;
    
    public VisitResponse(int totalVisits, String message) {
        this.totalVisits = totalVisits;
        this.message = message;
    }
}

class TimelineEvent {
    public String title;
    public String description;
    public String emoji;
    
    public TimelineEvent(String title, String description, String emoji) {
        this.title = title;
        this.description = description;
        this.emoji = emoji;
    }
}

class QuizResponse {
    public String answer1;
    public String answer2;
    
    public QuizResponse() {}
    
    public QuizResponse(String answer1, String answer2) {
        this.answer1 = answer1;
        this.answer2 = answer2;
    }
}

class QuizResult {
    public String result;
    public String title;
    public boolean success;
    
    public QuizResult(String result, String title, boolean success) {
        this.result = result;
        this.title = title;
        this.success = success;
    }
}

class HealthCheck {
    public String status;
    public boolean isHealthy;
    
    public HealthCheck(String status, boolean isHealthy) {
        this.status = status;
        this.isHealthy = isHealthy;
    }
}