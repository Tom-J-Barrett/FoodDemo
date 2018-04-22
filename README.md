# NutriLog
NutriLog is a nutritional assessment application created for my Final Year Project.
It is a prototype application aimed to demonstrate the use of a convolutional neural network that was trained on 108 different food classes.

NutriLog allows a user to select an image from their gallery or take a new image and sends it to an AWS EC2 instance to be classified using TensorFlow.
NutriLog then queries the Nutrionix API to record calorie information on the classification and this data is saved for user metrics.
