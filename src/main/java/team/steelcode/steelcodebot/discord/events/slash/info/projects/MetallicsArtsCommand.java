package team.steelcode.steelcodebot.discord.events.slash.info.projects;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class MetallicsArtsCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "metallics_arts";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        String message = """
                ## Metallics Arts!
                
                Immerse yourself in the world of "Mistborn" by Brandon Sanderson 💫. We highly recommend familiarizing yourself with the saga before diving into this mod. And while you're at it, don't miss out on the author's other fantastic works!
                
                > Github: [Metallics Arts](<https://github.com/SteelCodeTeam/Metallics-Arts>)
                > CurseForge: [Metallics Arts](<https://www.curseforge.com/minecraft/mc-mods/metallics-arts>)
                > Modrinth: [Metallics Arts](<https://modrinth.com/mod/metallics-arts>)
               
                *Road to 10k downloads! :D*
                """;

        return event.reply().withContent(message);
    }
}
