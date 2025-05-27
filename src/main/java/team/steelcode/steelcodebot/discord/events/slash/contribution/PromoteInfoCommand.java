package team.steelcode.steelcodebot.discord.events.slash.contribution;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class PromoteInfoCommand implements ISlashCommand  {
    @Override
    public String getName() {
        return "promote_info";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String message = """
                # 🔧 Contribution and Promotion System in SteelCodeTeam
                
                The **Contributions** and **Promotions** system is designed to recognize, and reward the contributions of members who regularly help us. Below, we explain how the system works and how it's structured:
                
                ## 📝 **Contributions**
                Contributions are the primary way you can earn **Points**. Every time you make a contribution to the project, you can receive a certain number of points based on the impact of your contribution.Not all contributions will be worth points, but a continuous stream of contributions where no points are received can also earn you points. Only SteelCodeTeam members can assign a number of points to a user.
                
                ## 📊 **Points Check**
                Any user can check their points, or the points of another user, to see how much they have contributed to the project.
                
                ### Command: `get_contribution`
                The `/get_contribution` command allows users and administrators to check how many points you have accumulated.
                
                ### Command: `ranking`
                The `/ranking` command shows the top 5 users by points.

                ## 🎖️ **Promotion System**
                The promotion system rewards users with special roles based on the number of points they accumulate. These roles reflect their level of commitment, trust, and skills within the community. When you promote, you will be notified and given the new rank if everything is okay.
                
                ### Command: `promote`
                Users can run `/promote` to check if they qualify for the next available rank.
                
                *Other useful commands:* `/guidelines_ranks`, `/point_list`
                
                *We hope that with this you all try to contribute a little more! :D*
                """;



        return event.reply(message).withEphemeral(true);
    }
}
