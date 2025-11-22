package com.example.sharesphere.config;

import com.example.sharesphere.model.Tool;
import com.example.sharesphere.repo.ToolRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod") // only load when NOT running with 'prod' profile (optional)
public class DevDataLoader implements CommandLineRunner {

    private final ToolRepo repo;

    public DevDataLoader(ToolRepo repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repo.count() > 0) {
            // already seeded
            return;
        }

        Tool t1 = Tool.builder()
                .name("8ft Ladder")
                .description("Sturdy aluminum ladder, up to 8ft height.")
                .ownerId(2L)
                .available(true)
                .location("Hyderabad")
                .pricePerHour(5.0)
                .build();

        Tool t2 = Tool.builder()
                .name("Cordless Drill")
                .description("18V cordless drill with two batteries.")
                .ownerId(1L)
                .available(true)
                .location("Bengaluru")
                .pricePerHour(12.0)
                .build();

        Tool t3 = Tool.builder()
                .name("Electric Paint Sprayer")
                .description("Good for medium size painting jobs, easy to clean.")
                .ownerId(3L)
                .available(false)
                .location("Chennai")
                .pricePerHour(20.0)
                .build();

        repo.save(t1);
        repo.save(t2);
        repo.save(t3);

        System.out.println("Seeded sample tools: " + repo.count());
    }
}
