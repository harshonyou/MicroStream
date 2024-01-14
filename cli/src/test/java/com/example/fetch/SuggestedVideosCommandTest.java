package com.example.fetch;

import com.example.Cli;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class SuggestedVideosCommandTest {
    @Test
    public void testWithCommandLineOption() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(baos));

        try (ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)) {
            String[] args = new String[]{"suggest-videos", "-u", "123", /*"-t", "video",*/ "--verbose"};
            PicocliRunner.run(Cli.class, ctx, args);
            out.println(baos);
            // cli
        }
    }
}