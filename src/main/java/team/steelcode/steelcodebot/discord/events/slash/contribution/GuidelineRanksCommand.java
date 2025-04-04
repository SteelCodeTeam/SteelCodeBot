package team.steelcode.steelcodebot.discord.events.slash.contribution;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class GuidelineRanksCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "guideline_ranks";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        String message = """
                ## 🎖️ **Ranks Overview**
                This guide explains the roles available in the project, their requirements, and what they represent. These ranks help recognize the effort and dedication of contributors toward the community.
                
                ### Ranks and Requirements:
                1. **Helper** 🛠️
                    - Requirement: **10 points**
                    - Description: This rank represents those who have begun occasionally supporting the project.
                
                2. **Collaborator** 🌟
                    - Requirement: **25 points**
                    - Description: Active members with a growing impact, contributing actively.
                
                3. **Discord Staff** 👨‍💻
                    - Requirement: **50 points**
                    - Description: Active members with moderation rights on Discord, who frequently give their opinions and contribute to different projects.
                
                4. **SteelCodeTeam** 🏆
                    - Requirement: **100 points**
                    - Description: This is the ultimate rank. It represents complete confidence in your abilities and ongoing commitment to the project. In addition to points, this rank requires a personal evaluation from the team.
                """;

        return event.reply().withContent(message).withEphemeral(true);
    }
}
