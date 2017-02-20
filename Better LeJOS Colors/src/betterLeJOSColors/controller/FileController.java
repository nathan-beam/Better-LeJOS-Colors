package betterLeJOSColors.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import betterLeJOSColors.model.Color;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Controller for reading and writing color save file.
 * 
 * @author Nathan Beam 
 * @version Fall 2016
 */
public class FileController {

	/**
	 * Returns true if the file exists and has the required number of entries
	 * (9)
	 * @param filepath the path of the file to test
	 *
	 * @precondition none
	 * @postcondition none
	 * @return whether the file can be used for analysis
	 */
	public static boolean testForFile(String filepath) {
		File colorFile = new File(filepath);
		try {
			int size = FileController.loadColors(filepath).size();
			return (size >= 9 && colorFile.exists());
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Saves a ScanList of colors to the saves directory in .arff format
	 *
	 * @precondition none
	 * @postcondition the ScanList is saved as an analyzable list
	 * @param scanList
	 *            the ScanList of colors to save
	 * @param filepath the filepath to save to
	 * @throws IOException
	 *             Exception saving file
	 */
	public static void saveColors(ScanList scanList, String filepath) throws IOException {
		Attribute red = new Attribute("red");
		Attribute green = new Attribute("green");
		Attribute blue = new Attribute("blue");

		Attribute colorNames = new Attribute("colorNames", scanList.getAllColorNames());
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(red);
		attributes.add(green);
		attributes.add(blue);
		attributes.add(colorNames);

		Instances training = new Instances("Colors", attributes, 4);

		training.setClassIndex(3);

		for (Color color : scanList.getScanList()) {
			Instance instance = new DenseInstance(4);
			instance.setValue((Attribute) attributes.get(0), color.getRed());
			instance.setValue((Attribute) attributes.get(1), color.getGreen());
			instance.setValue((Attribute) attributes.get(2), color.getBlue());
			instance.setValue((Attribute) attributes.get(3), color.getColorName());

			training.add(instance);
		}
		Writer out = new BufferedWriter(new FileWriter(filepath));
		out.write(training.toString());
		out.close();

	}

	/**
	 * Loads the Color objects from a saved .arff file
	 * @param filepath the file path to load from
	 *
	 * @precondition there exists a saved .arff file
	 * @postcondition none
	 * @return the Color objects from the .arff file
	 * @throws IOException
	 *             Exception reading file
	 */
	public static ArrayList<Color> loadColors(String filepath) throws IOException {
		ArrayList<Color> colors = new ArrayList<Color>();
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line = br.readLine();
		boolean data = false;
		while (line != null) {
			if (line.startsWith("@data")) {
				line = br.readLine();
				data = true;
			}
			if (data) {
				String[] attrs = line.split(",");
				float red = Float.parseFloat(attrs[0]);
				float green = Float.parseFloat(attrs[1]);
				float blue = Float.parseFloat(attrs[2]);
				Color color = new Color(red, green, blue);
				color.setColorName(attrs[3]);
				colors.add(color);
			}
			line = br.readLine();
		}
		br.close();

		return colors;
	}

	/**
	 * Gets the training data from the .arff file
	 * @param filepath The file path to load from
	 *
	 * @precondition none
	 * @postcondition none
	 * @return Weka Instances object representing the training data
	 * @throws IOException Execption reading file
	 */
	public static Instances getTrainingData(String filepath) throws IOException {
		Instances trainingData = null;
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		trainingData = new Instances(br);
		trainingData.setClassIndex(3);
		br.close();

		return trainingData;

	}

}
