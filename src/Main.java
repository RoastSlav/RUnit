import org.apache.commons.cli.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    private static final String PROGRAM_NAME = "RUnit";
    private static final HelpFormatter FORMATTER = new HelpFormatter();
    private static CommandLine cmd = null;

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
        Options options = intializeOptions();
        CommandLineParser cmdParser = new DefaultParser();
        try {
            cmd = cmdParser.parse(options, args);
            if (cmd.hasOption("help")) {
                FORMATTER.printHelp(PROGRAM_NAME, options, true);
                return;
            }
        } catch (ParseException e) {
            FORMATTER.printHelp(PROGRAM_NAME, options, true);
            return;
        }

        if (cmd.hasOption("t")) {
            String f = cmd.getOptionValue("t");

            Class<?> ca = Class.forName(f);
            RUnitCore.runTests(ca);
        }
    }

    private static Options intializeOptions() {
        Options options = new Options();
        //Temp option for testing remove later
        Option test = Option.builder("t").hasArg().build();
        options.addOption(test);
        Option uri = Option.builder("u").longOpt("select-uri").argName("URI").hasArg().desc("URI for test discovery").build();
        options.addOption(uri);
        Option file = Option.builder("f").longOpt("select-file").argName("FILE").hasArg().desc("File for test discovery").build();
        options.addOption(file);
        return options;
    }
}