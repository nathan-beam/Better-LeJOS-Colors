package betterLeJOSColors.controller;

import java.io.IOException;
import java.util.ArrayList;

import betterLeJOSColors.controller.ScanList;
import betterLeJOSColors.controller.FileController;
import betterLeJOSColors.model.Color;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Handles the classification of Colors
 * 
 * @author Nathan Beam 
 * @version Fall 2016
 */
public class Classifier {

	private String filePath;
	private ScanList scanlist;
	private RandomForest rf;
	private Instances trainingData;
	/**
	 * Constructor for a new Classifier
	 * @param filePath
	 *            the path of the save file
	 */
	public Classifier(String filePath) {
		this.filePath = filePath;
		this.scanlist = new ScanList();
	}
	
	/**
	 * Saves the current scanlist data to the specified color path
	 *
	 * @precondition none
	 * @postcondition the scanlist is saved to disk
	 * @throws IOException Exception Saving
	 */
	public void save() throws IOException {
		FileController.saveColors(this.scanlist, this.filePath);
	}

	/**
	 * Adds a Color object to this scanlist
	 *
	 * @precondition none
	 * @postcondition the specified Color is added to the internal ScanList
	 * @param color the Color to add to the internal ScanList
	 */
	public void addColor(Color color) {
		this.scanlist.addColor(color);
	}
	
	/**
	 * Generates the Random Forest Classifier to use to classify the colors
	 *
	 * @throws Exception Unknown Exception
	 * @precondition none
	 * @postcondition the Random Forest is ready to classify
	 */
	public void generateClassifier() throws Exception {
		this.trainingData = FileController.getTrainingData(this.filePath);
		this.rf = new RandomForest();
		this.rf.buildClassifier(this.trainingData);
	}
	
	/**
	 * Classifies a Color Object by returning its string value
	 *
	 * @precondition color != null
	 * @postcondition none
	 * @param color the Color object to classify
	 * @return String name of the Color
	 * @throws Exception Unknown Exeption 
	 */
	public String classifyColor(Color color) throws Exception {
		if (color == null) {
			throw new IllegalArgumentException("Color was Null");
		}
		Instances instances = this.getInstancesFromColor(color);
		int labelIndex = (int) this.rf.classifyInstance(instances.lastInstance());
		return this.trainingData.attribute(3).value(labelIndex);
	}

	private Instances getInstancesFromColor(Color color) {
		Instances newData = new Instances(this.trainingData);
		newData.attribute(3).addStringValue("NEW");
		Instance instance = new DenseInstance(4);
		instance.setValue((Attribute) newData.attribute(0), color.getRed());
		instance.setValue((Attribute) newData.attribute(1), color.getGreen());
		instance.setValue((Attribute) newData.attribute(2), color.getBlue());
		instance.setValue((Attribute) newData.attribute(3), newData.attribute(3).value(0));

		newData.add(instance);
		return newData;
	}

	/**
	 * Loads the Color object from the file and sets the internal scanlist to them
	 *
	 * @throws IOException Exception loading file
	 * @precondition none
	 * @postcondition the file is loaded into the internal ScanList
	 */
	public void loadColors() throws IOException {
		ArrayList<Color> scanList = FileController.loadColors(this.filePath);
		this.scanlist.setScanList(scanList);
		
	}

	/**
	 * Checks that the specified File exists and is usable for classifying (>8 data points)
	 *
	 * @precondition none
	 * @postcondition none
	 * @return Whether the file exists and is good
	 */
	public boolean fileExistsAndIsGood() {
		return FileController.testForFile(this.filePath);
	}
}
