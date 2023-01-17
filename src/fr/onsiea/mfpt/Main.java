package fr.onsiea.mfpt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Main
{
	public final static void main(final String[] argsIn)
	{
		final var toReplaces = Main.readSettings(Settings.settingsFilepath);
		for (final Entry<String, String> entry : toReplaces.entrySet())
		{
			final var	from	= entry.getKey();
			final var	to		= entry.getValue();

			Settings.toReplaces.put(from, to);
		}

		final var					contents			= Main.readAllContents(new File(Settings.sourceFilepath),
				new HashMap<>());

		final Map<String, String>	replacedContents	= new HashMap<>();

		for (final Entry<String, String> entry : contents.entrySet())
		{
			final var	filepath	= entry.getKey();
			var			content		= entry.getValue();

			for (final Entry<String, String> replaceEntry : Settings.toReplaces.entrySet())
			{
				final var	from	= replaceEntry.getKey();
				final var	to		= replaceEntry.getValue();

				if (from.matches("^[a-zA-Z0-9]+$"))
				{
					content = content.replaceAll("\b" + Pattern.quote(from) + "\b", to)
							.replaceAll("[^a-zA-Z0-9[\\s+]]+" + Pattern.quote(from) + "[^a-zA-Z0-9[\\s+]]+", to);
				}
				else
				{
					content = content.replaceAll(Pattern.quote(from), to);
				}
			}

			replacedContents.put(filepath, content);

			final var file = new File(filepath);

			if (!file.getParentFile().exists())
			{
				file.getParentFile().mkdirs();
			}

			BufferedWriter writer = null;
			try
			{
				final var destination = new File(Settings.DestinationFilepath + file.getAbsolutePath()
						.substring(Settings.sourceFilepath.length(), file.getAbsolutePath().length()));

				writer = new BufferedWriter(new FileWriter(destination));
				writer.write(content);
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (writer != null)
				{
					try
					{
						writer.close();
					}
					catch (final IOException e)
					{
						e.printStackTrace();
					}
				}
			}

		}
	}

	public final static Map<String, String> readSettings(final String settingsFilepathIn)
	{
		final Map<String, String>	toReplaces	= new LinkedHashMap<>();

		String						line;
		BufferedReader				reader		= null;
		try
		{
			reader = new BufferedReader(new FileReader(settingsFilepathIn));

			var	block			= "";
			var	blockIsStarted	= false;

			while ((line = reader.readLine()) != null)
			{
				if (!blockIsStarted && Main.removeStartAndLastSpaces(line).startsWith("{"))
				{
					blockIsStarted = true;
				}
				else if (blockIsStarted)
				{
					if (Main.removeStartAndLastSpaces(line).endsWith("}"))
					{
						block += line.substring(0, line.length() - 1);
						Main.readSettingsBlock(block);
						block			= "";
						blockIsStarted	= false;
					}
					else
					{
						block += line + "\n";
					}
				}
				else if (!line.matches("\\s+") && line.length() > 0)
				{
					final var splits = Main.readSettingsLine(line);

					System.out.println(splits.get(0) + " > " + splits.get(1));
				}
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (final IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return toReplaces;
	}

	public final static void readSettingsBlock(final String blockIn)
	{
		System.out.println(blockIn);
	}

	private final static Pattern p = Pattern.compile("\"[^\"]*\"");

	public final static List<String> readSettingsLine(final String lineIn)
	{
		final var			m			= Main.p.matcher(lineIn);

		final List<String>	contents	= new ArrayList<>();
		while (m.find())
		{
			contents.add(m.group());
		}

		return contents;
	}

	public final static String replaceWholeWorld(final String fromIn, final String toIn, final String contentIn)
	{
		var	content	= "";
		var	line	= "";
		for (var i = 0; i < contentIn.length(); i++)
		{
			final var character = contentIn.substring(i, i + 1);

			if (character.matches("^[a-zA-Z0-9]+$"))
			{
				line += character;
			}
			else
			{

				if (line.contentEquals(fromIn))
				{
					line = toIn;
				}

				content	+= line;
				content	+= character;
				line	= "";
			}
		}

		return content;
	}

	public final static String removeStartAndLastSpaces(final String contentIn)
	{
		var	content		= "";
		var	space		= "";

		var	isStarted	= false;

		for (var i = 0; i < contentIn.length(); i++)
		{
			final var character = contentIn.substring(i, i + 1);

			if (!character.matches("\\s+"))
			{
				isStarted	= true;
				content		+= space;
				space		= "";
				content		+= character;
			}
			else if (isStarted)
			{
				space += character;
			}
		}

		return content;
	}

	public final static Map<String, String> readAllContents(final File fileIn, final Map<String, String> contentsIn)
	{
		if (!fileIn.isFile() && fileIn.listFiles() != null)
		{
			for (final File file : fileIn.listFiles())
			{
				Main.readAllContents(file, contentsIn);
			}
		}
		else if (fileIn.getAbsolutePath().endsWith(".java"))
		{
			BufferedReader reader = null;
			try
			{
				reader = new BufferedReader(new FileReader(fileIn));

				var		content	= "";
				String	line;

				while ((line = reader.readLine()) != null)
				{
					content += line + "\n";
				}

				contentsIn.put(fileIn.getAbsolutePath(), content);
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (reader != null)
				{
					try
					{
						reader.close();
					}
					catch (final IOException e)
					{
						e.printStackTrace();
					}
				}
			}

		}

		return contentsIn;
	}
}
