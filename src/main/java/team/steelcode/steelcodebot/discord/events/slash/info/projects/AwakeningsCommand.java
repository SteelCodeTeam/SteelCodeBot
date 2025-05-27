package team.steelcode.steelcodebot.discord.events.slash.info.projects;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class AwakeningsCommand implements ISlashCommand {

    @Override
    public String getName() {
        return "awakenings";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        String message = """
                ## Awakenings!
                
                Immerse yourself in the world of "Warbreaker" by Brandon Sanderson 💫. We highly recommend familiarizing yourself with the book before diving into this mod. And while you're at it, don't miss out on the author's other fantastic works!
                
                > Github: [Awakenings](<https://github.com/SteelCodeTeam/Awakenings>)
                > CurseForge: [Awakenings](<https://www.curseforge.com/minecraft/mc-mods/awakenings>)
                > Modrinth: [Awakenings](<https://modrinth.com/mod/awakenings>)
               
                *Road to 1k downloads! :D*
                """;

        return event.reply().withContent(message);
    }
}
