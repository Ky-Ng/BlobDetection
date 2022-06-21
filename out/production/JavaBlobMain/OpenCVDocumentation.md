Since there is vry little OpenCV documentation for Java, here is my own notes that I had to brute force debug!

OpenCV Coordinate System
(Found out the hard way by brute forcing for loops over mats)


         
View in nonpreview mode
-----------> cols (x) +
|
|
|
| Image Here
|
|
+ rows (y)


Example: the letter "r" is (9, 4)


Note that in the MatPixel.getColor()[0,1,2] channel 

[0] = B
[1] = G
[2] = R



