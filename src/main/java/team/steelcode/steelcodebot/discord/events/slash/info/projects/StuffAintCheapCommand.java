package team.steelcode.steelcodebot.discord.events.slash.info.projects;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class StuffAintCheapCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "stuff_aint_cheap";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        String message = """
                ## Stuff Ain't Cheap!
                
                Villagers no longer make diamond items out of thin air.
                
                > Github: [Metallics Arts](<https://github.com/SteelCodeTeam/StuffAintCheap>)
                > CurseForge: [Metallics Arts](<https://www.curseforge.com/minecraft/mc-mods/stuff-ain-t-cheap>)
                > Modrinth: [Metallics Arts](<https://modrinth.com/mod/stuff-aint-cheap>)
               
                *Road to 25k downloads!* *__WOW__*
                """;

        return event.reply().withContent(message);
    }
}