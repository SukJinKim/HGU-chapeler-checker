package hgu.alinew;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ChapelerCheckerRunner {
	private String youtubeURL;
	private String chromeDriverPath;
	private String professorName;
	private boolean help;

	public static void main(String[] args) {
		ChapelerCheckerRunner runner = new ChapelerCheckerRunner();
		
		try {
			runner.run(args);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("u")
								.desc("Youtube URL (ex. https://www.youtube.com/watch?v=vW6dzGwpSTM)")
								.hasArg()
								.required(true)
								.build());

		options.addOption(Option.builder("p")
								.desc("Chrome Driver Path")
								.hasArg()
								.required(true)
								.build());

		options.addOption(Option.builder("n")
								.desc("Professor Name")
								.hasArg()
								.required(true)
								.build());

		options.addOption(Option.builder("h")
								.longOpt("help")
								.desc("Help")
								.build());

		return options;
	}

	// Parsing Stage
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		CommandLine cmd;

		try {
			cmd = parser.parse(options, args);

			youtubeURL = cmd.getOptionValue('u');
			chromeDriverPath = cmd.getOptionValue('p');
			professorName = cmd.getOptionValue('n');
			help = cmd.hasOption('h');

		} catch (ParseException e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Interrogation Stage
	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();

		String header = "Crawling comments in HGU university church youtube video to check his/her attendance\n\n";
		String footer = "\nPlease report issues at https://github.com/SukJinKim/HGU-chapeler-checker\n\n";

		formatter.printHelp("HGU-chepeler-checker", header, options, footer, true);
	}

	private void run(String[] args) throws IOException, InterruptedException {
		Options options = createOptions();

		if (parseOptions(options, args)) {
			if (help) {
				printHelp(options);
				return;
			}

			// Input Info
			System.out.println("\n============== Input Info ==============");
			System.out.println("Youtube URL : " + youtubeURL);
			System.out.println("Chrome Driver Path : " + chromeDriverPath);
			System.out.println("Professor's name : " + professorName);
			System.out.println("========================================\n");
			
			ChapelerChecker checker = new ChapelerChecker(youtubeURL, chromeDriverPath, professorName);
			checker.run();
		}
	}
}
