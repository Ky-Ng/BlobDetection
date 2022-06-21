Since there is vry little OpenCV documentation for Java, here is my own notes that I had to brute force debug!

OpenCV Coordinate System
(Found out the hard way by brute forcing for loops over mats)


         
View in nonpreview mode
Points (OpenCV Default) uses the below graph which is correct

However, the mat.Get(x,y) returns a double[] uses (y,x) or (row,col)
O-----------> cols (x) +
|
|
|
| Image Here
|
|
v
+ rows (y)


Example: the letter "r" is (9, 4)

So there's a difference between the coordinate system for Points and for Mats
https://stackoverflow.com/questions/25642532/opencv-pointx-y-represent-column-row-or-row-column



Note that in the MatPixel.getColor() and Imgproc.rectangle (new Scalar) use [0,1,2] channel 

[0] = B
[1] = G
[2] = R

Ok after fiddling with the colors for 10^x amt of time, there seems to be issues with green and blue so when the actual image is to be looked for, instead just find the corresponding RGB value in OpenCV and set as the target

