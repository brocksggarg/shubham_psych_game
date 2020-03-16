package com.psych.game.controller;

import com.psych.game.model.*;
import com.psych.game.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dev-test")
public class DevTestController {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameModeRepository gameModeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoundRepository roundRepository;
    @Autowired
    private ContentWriterRepository contentWriterRepository;
    @Autowired
    private EllenAnswerRepository ellenAnswerRepository;

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/populate")
    public String populateDB() {
        for (Player player : playerRepository.findAll()) {
            player.getGames().clear();
            player.setCurrentGame(null);
            playerRepository.save(player);
        }
        gameRepository.deleteAll();
        playerRepository.deleteAll();
        questionRepository.deleteAll();
        gameModeRepository.deleteAll();

        Player luffy = new Player.Builder()
                .alias("Monkey D. Luffy")
                .email("luffy@psych.com")
                .saltedHashedPassword("strawhat")
                .build();
        playerRepository.save(luffy);
        Player robin = new Player.Builder()
                .alias("Nico Robin")
                .email("robin@psych.com")
                .saltedHashedPassword("poneglyph")
                .build();
        playerRepository.save(robin);

        GameMode isThisAFact = new GameMode("Is This A Fact?", "https://i.pinimg.com/originals/29/cb/75/29cb75e488831ba018fe5f0925b8e39f.png", "is this a fact description");
        gameModeRepository.save(isThisAFact);
        gameModeRepository.save(new GameMode("Word Up", "https://i.pinimg.com/originals/29/cb/75/29cb75e488831ba018fe5f0925b8e39f.png", "word up description"));
        gameModeRepository.save(new GameMode("Un-Scramble", "https://i.pinimg.com/originals/29/cb/75/29cb75e488831ba018fe5f0925b8e39f.png", "unscramble descirption"));
        gameModeRepository.save(new GameMode("Movie Buff", "https://i.pinimg.com/originals/29/cb/75/29cb75e488831ba018fe5f0925b8e39f.png", "movie buff description"));

        Game game = new Game();
        game.setGameMode(isThisAFact);
        game.setLeader(luffy);
        game.getPlayers().add(luffy);
        gameRepository.save(game);

        questionRepository.save(new Question(
                "What is the most important Poneglyph",
                "Rio Poneglyph",
                isThisAFact
        ));

        questionRepository.save(new Question(
                "How far can Luffy stretch?",
                "56 Gomu Gomus",
                isThisAFact
        ));

        return "populated";
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/question/{id}")
    public Optional<Question> getQuestionById(@PathVariable(name = "id") Long id) {
        return questionRepository.findById(id);
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/player/{id}")
    public Optional<Player> getPlayerById(@PathVariable(name = "id") Long id) {
        return playerRepository.findById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable(name = "id") Long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/game/{id}")
    public Optional<Game> getGameById(@PathVariable(name = "id") Long id) {
        return gameRepository.findById(id);
    }

    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @GetMapping("/admin/{id}")
    public Optional<Admin> getAdminById(@PathVariable(name = "id") Long id) {
        return adminRepository.findById(id);
    }

    @GetMapping("/rounds")
    public List<Round> getAllRounds() {
        return roundRepository.findAll();
    }

    @GetMapping("/round/{id}")
    public Optional<Round> getRoundById(@PathVariable(name = "id") Long id) {
        return roundRepository.findById(id);
    }

    @GetMapping("/contentWriters")
    public List<ContentWriter> getAllContentWriters() {
        return contentWriterRepository.findAll();
    }

    @GetMapping("/contentWriter/{id}")
    public Optional<ContentWriter> getContentWriterById(@PathVariable(name = "id") Long id) {
        return contentWriterRepository.findById(id);
    }

    @GetMapping("/ellenAnswers")
    public List<EllenAnswer> getAllEllenAnswers() {
        return ellenAnswerRepository.findAll();
    }

    @GetMapping("/ellenAnswer/{id}")
    public Optional<EllenAnswer> getEllenAnswerById(@PathVariable(name = "id") Long id) {
        return ellenAnswerRepository.findById(id);
    }
}