package com.example.feedback;

import com.example.CliCommand;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DislikeVideoTest {
    @Test
    public void testWithCommandLineOption() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(baos));

        try (ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)) {
            String[] args = new String[]{"dislike-video", "-u", "123", "-v", "cfcd04f0-9f1a-11ee-995e-fffdb2b698b7", "--verbose"};
            PicocliRunner.run(CliCommand.class, ctx, args);
            out.println(baos);
            // cli
        }
    }
}