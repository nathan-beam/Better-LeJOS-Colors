# Better-LeJOS-Colors
A library for LeJOS color recognition via Random Forests using the WEKA library. 


To use:

1. Download the importable jar
2. Import into your java project
3. Create new Classifier, passing in filename as constructor parameter
4. Create a color by instantiating with red, green, blue values obtained from lojos color sensor
5. Set color name
6. Call Classifier.addColor(), passing in color
7. Repeat at least 8 times for a total of 9 scans (reccomended at least 3 scans per color/shade)

Classification:

1. Call Classifier.generateClassifier() to build random forest  
2. Create new Color from color sensor  
3. Pass into Classifier.ClassifyColor, which will return the name of the closest matching color.  

