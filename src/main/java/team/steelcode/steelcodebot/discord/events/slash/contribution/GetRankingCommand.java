package team.steelcode.steelcodebot.discord.events.slash.contribution;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import team.steelcode.steelcodebot.discord.events.ISlashCommand;
import team.steelcode.steelcodebot.persistence.entity.UserEntity;
import team.steelcode.steelcodebot.persistence.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class GetRankingCommand implements ISlashCommand {

    private final UserRepository userRepository;

    public GetRankingCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getName() {
        return "ranking";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {

        String message = "";

        String intro = "# **Our best contributors are:**\n\n";

        String firstLine = "## 1. :crown: @%s :crown: *(with %s points)*\n";
        String secondLine = "### 2. :trophy: @%s :trophy: *(with %s points)*\n";
        String thirdLine = "### **3. :wine_glass: @%s :wine_glass: *(with %s points)***\n";
        String fourthLine = " 4. @%s *(with %s points)*\n";
        String fifthLine = " 5. @%s *(with %s points)*";


        List<UserEntity> optUserList = userRepository.findAll();


        if (!optUserList.isEmpty()) {
            optUserList.sort((ue1, ue2) -> (int) (ue2.getPoints() - ue1.getPoints()));

            message = intro;

            message = message + firstLine.formatted(optUserList.get(0).getName(), optUserList.get(0).getPoints());
            if (optUserList.size() > 1) {
                message = message + secondLine.formatted(optUserList.get(1).getName(), optUserList.get(1).getPoints());
            }
            if (optUserList.size() > 2) {
                message = message + thirdLine.formatted(optUserList.get(2).getName(), optUserList.get(2).getPoints());
            }
            if (optUserList.size() > 3) {
                message = message + fourthLine.formatted(optUserList.get(3).getName(), optUserList.get(3).getPoints());
            }
            if (optUserList.size() > 4) {
                message = message + fifthLine.formatted(optUserList.get(4).getName(), optUserList.get(4).getPoints());
            }

            message = message + "\n\n *Thanks a lot for all your help. All of you give our life more easy :)*";

            return event.reply(message);
        } else {
            return event.reply("Nobody has contributions yet!");
        }
    }
}

