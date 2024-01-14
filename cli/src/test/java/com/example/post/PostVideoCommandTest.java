package com.example.post;

import com.example.Cli;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class PostVideoCommandTest {
    @Test
    public void testWithCommandLineOption() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(baos));

        try (ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)) {
            String[] args = new String[]{"post", "-u", "123", "-t", "hey! first video", "-T", "first", "-T", "video", "--verbose"};
            PicocliRunner.run(Cli.class, ctx, args);
            out.println(baos);
            // cli
        }
    }
}