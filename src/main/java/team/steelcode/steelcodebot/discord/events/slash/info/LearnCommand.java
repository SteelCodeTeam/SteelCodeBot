package team.steelcode.steelcodebot.discord.events.slash.info;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;

@Service
public class LearnCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "learn";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        Mono<User> monoUser = event.getOption("user")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asUser)
                .orElse(null);

        String tutorial = event.getOption("course")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse(null);

        User discUser = monoUser.block();

        if (tutorial == null) {
            return event.reply("You can see all our published courses in <#1191755326626734152>!");
        }

        if (monoUser == null) {
            return event.reply("User doesnt exist").withEphemeral(true);
        }

        if (tutorial.equalsIgnoreCase("java")) {

            return event.reply("""
                            **Hey <@%s>!**
                            
                            We have a course for developers that may be useful if you already know other languages or have an intermediate level.
                            
                            [You can see them here](<https://youtu.be/19WT63GrZH0>) (Only in Spanish)
                    
                            *We hope you enjoy them.*
                            """.formatted(discUser.getId().asString()));

        } else if (tutorial.equalsIgnoreCase("neoforge") || tutorial.equalsIgnoreCase("neoforged")
                        || tutorial.equalsIgnoreCase("modding") || tutorial.equalsIgnoreCase("forge")) {

            return event.reply("""
                            **Hey <@%s>!**
                            
                            We have a course to learn basic Forge/NeoForge modding, which will give you the necessary skills to contribute to the team. If you have no knowledge of Java, check out our Java tutorials.
                            
                            [You can see them here](<https://www.youtube.com/playlist?list=PLl7QOG3x4R6gpvbcMZNZNeiaSTcChGC-n>) (Only in Spanish)
                    
                            *We hope you enjoy them.*
                            """.formatted(discUser.getId().asString()));

        } else if (tutorial.equalsIgnoreCase("spring") || tutorial.equalsIgnoreCase("springboot")
                        || tutorial.equalsIgnoreCase("spring-boot") || tutorial.equalsIgnoreCase("api")
                        || tutorial.equalsIgnoreCase("rest") || tutorial.equalsIgnoreCase("restful")) {

            return event.reply("""
                            **Hey <@%s>!**
                            
                            We have a course where we teach the basics of Springboot for beginners.
                            
                            [You can see them here](<https://www.youtube.com/playlist?list=PLl7QOG3x4R6gcU_HdsBWKbW3WH078UxPo>) (Only in Spanish)
                    
                            *We hope you enjoy them.*
                            """.formatted(discUser.getId().asString()));


        } else {
            return event.reply("The parameter you used does not exist, but you can see all our published courses in <#1191755326626734152>!");
        }

    }
}
