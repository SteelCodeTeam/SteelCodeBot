package team.steelcode.steelcodebot.discord.events.slash.contribution;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class PointListCommand implements ISlashCommand {


    @Override
    public String getName() {
        return "point_list";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        String message = """
                ## 💡 **Guideline Point Assessments**
                This table contains guideline score values. The value of each individual contribution will be evaluated individually, but this can serve as a general guideline.

                ```haxe
                Critical Bug Report: 1 point
                Make a Suggestion (implemented): 1 point
                Contributing to a Project Wiki: 1 point
                Technical Support for Other Users: 1 point
                Update project descriptions on Github, CurseForge, or Modrinth: 1 point 
                                
                Documentation/Videos/Guides: 2 points
                Creating or Configuring CI/CD: 2 points
                
                Develop a Bug Fix: 3 points
                Develop a New Feature: 3 points
                Contribute Assets (graphics, music, translations, etc.): 3 points
                
                Creating to a Project Wiki: 4 points
                Creating Functional Prototypes (New Projects, Suggestions, etc.): 4 points
                
                Continued Collaboration on a Beta: 5 points
                Continued Community Management (Discord, CF, Modrinth, Social Media, Minecraft Servers): 5 points  
                ```
                
                It's important to understand that not all contributions are worth the same, and there will be contributions that aren't reflected in the list.
                
                We've tried to create various ways to earn points, targeting different skills (soft skills, technical knowledge, beta testing, development, artists, etc.).
                
                """;

        return event.reply().withContent(message).withEphemeral(true);
    }
}
