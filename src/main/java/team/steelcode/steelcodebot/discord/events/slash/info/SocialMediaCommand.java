package team.steelcode.steelcodebot.discord.events.slash.info;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class SocialMediaCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "social";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        String message = """
                ## Follow us!
                > Twitter: [SteelCodeTeam](<https://twitter.com/SteelCodeTeam>)
                > Bluesky: [SteelCodeTeam.bsky.social](<https://bsky.app/profile/steelcodeteam.bsky.social>)
                > Twitch: [SteelCodeTeam](<https://twitch.tv/SteelCodeTeam>)
                > Youtube: [SteelCodeTeam](<https://www.youtube.com/@SteelCodeTeam>)
                > Github: [SteelCodeTeam](<https://github.com/SteelCodeTeam>)
                > Reddit: [SteelCodeTeam](<https://www.reddit.com/user/SteelCodeTeam/>)
                > Web: [SteelCodeTeam](<https://steelcode.team/>) *Under construction*
                > Ko-Fi: [SteelCodeTeam](<https://ko-fi.com/steelcodeteam>)
                > Email: [contact@steelcode.team](mailto:contact@steelcode.team)
               
                *Thanks for all of your support!*
                """;

        return event.reply().withContent(message);
    }
}
