# BlobDetection
BlobDetection is a custom Computer Vision Algorithm based on Blob Detection using OpenCV written in Java. This algorithm only uses OpenCV for image display and pixel color reading from images; Inspired by the JavaScript PS5 tutorial by the [Coding Train]([url](https://www.youtube.com/watch?v=ce-2l2wRqO8)).

# How the Algorithm Works
**What Are Blobs?**
A Blob is a group of pixels that meet are similar in characteristics such as: Color, Distance, Shape, Convexity. This algorithm utilizes RGB Color and Distance.

**Blob Detection Logic**
The algorithm has three main steps:
1. **Find Blobs**: Identify pixels that are similar in color.
2. **Merge/Prune Blobs**: Merge similar colored pixels that are close together to form a Blob.
3. **Bounding Boxes**: Merge neighboring Blobs to create bounding boxes.


# Optimizations
**Decimation Inspired Processing**

In order to speed up the processing, the algorithm takes a _decimation_ parameter. Decimation is the Image Processing principle of removing every nth pixel in order to decrease the amount of times the program loops over an image which speeds up the processing.

This algorithm borrows _decimation_'s principle in the sense that only every nth pixel is processed rather than removing the nth pixel. Ex: Decimation parameter = 30; only every 30th pixel in each row and column is processed.

**Artifact Elimination**

During [Step 2, Merge/Prune Blobs](#How-The-Algorithm-Works), the algorithm goes through an ArrayList of Blobs and removes any Blob with area less than 5. These _Artifact Blobs_ sometimes form when a pixel is close to a specific but not close enough to the center distance of any of the neighboring Blobs. 

Future improvements can also merge the pixel if the pixel is within an existing blob; however, this may make the algorithm less robust because it may create _false blobs_.

# Using the Project
To use BlobDetection on a desired image, the desired parameters: ImageName, TargetRGB, Distance and Color Threshold, and Decimation

Found in /JavaBlobMain/src/Processing/Parameters:
```
    public static String AssetsFolder = "/Users/kyleng/IdeaProjects/BlobDetection/JavaBlobMain/src/Assets/";
    public static String ImageName = "blueFish.jpg";
    public static Scalar TargetRGB = new Scalar(43, 143, 253);
    public static int DistanceThreshold = 290;
    public static int ColorThreshold = 150;
    public static int Decimation = 30;
```

1. Replace the absolute path to the Assets folder.
2. Replace ImageName with the name of your desired image.
3. Run FindColorUI and enter the XY coordinates of the color on the image you wish to detect. The algorithm will create blobs similar to this color.
4. Replace TargetRGB with the RGB value returned in the console in Step 3.
5. Run Pipeline
6. Tweak  ColorThreshold, DistanceThreshold, and Decimation.

# How to Tweak Parameters

_ColorThreshold_: If the console returns "Empty Blob List" or _Blob Size_ at the end is smaller than you expect, increase the ColorThreshold so that the Blobs will include more shades of your TargetRGB. Based on the image processing windows provided, you can also decrease ColorThreshold if Blobs are forming around areas that are not desired since the algorithm is accepting too many shades of TargetRGB.

_Distance Threshold_: If the output says "Added New Blob" as the _Blob Size_ returned approaches infinity, increase Distance Threshold.
(What is happening is that blobs close to each other are not merging causing the program to create too many very small blobs)

_Decimation_: If the image bounding box formed around the image is not covering the whole image, this is likely because the Decimation is too high (skipping over too many pixels). Simply decrease Decimation; this will increase processing time (should be negligible for small images) and you will get more precise Blobs. If the algorithm is taking too long for large images (and DistanceThreshold and ColorThreshold are tuned correctly), increase decimation since you can skip over Pixels without degrading precision by too much.

**Project File Tree**
```
├── JavaBlobMain
│   └── Assets
│       ├── blueFish.jpg
│   └── src
│       ├── FindColorUI.java
│       ├── Pipeline.java
│       └── Processing
│           ├── Blob.java
│           ├── Parameters.java
│           └── Util
│               ├── BlobsUtil.java
│               ├── Drawer.java
│               └── Pixel.java
```
# Future Capabilities
**HSV Color Filters**

To improve the accepted shades of colors the Blobs will accept, converting the source image to HSV format will allow for greater robustness of the algorithm to detect the object of interest in different lighting conditions and allow for greater precision at the edge of blobs. (Real life objects/camera inputs are often darker at the sides because of natural lighting)

**Contours for Multi-Colored Images**

To detect multi-colored objects, the Blob constraint can also take the parameter of the shape of the objects in the image. This would require the use of a Canny or Classifier built into OpenCV. Currently, BlobDetection does not use any built-in OpenCV processing for the sake of learning and understanding how to do different Image Processing techniques from scratch.

**Camera Homography**

Homography is the Image Processing technique of translating the 2D bounding box image in the XY coordinate system of OpenCV (or any processing library) into 3D coordinates.

With Bounding Boxes yielded at the final stage of processing, a fiducial marker can also be placed beside the desired image to tune XYZ distance parameters to find the actual distance of the target object in relation to the camera. This requires more tuning with physical parameters. However, if the use case only is searching for whether the object is in frame or is in a certain part of the frame, Homography is not needed.

# Gallery
Below are the three steps with various cases:

![1 Source Mountain ](https://github.com/Radagrass-the-infinite/BlobDetection/blob/main/JavaBlobMain/src/Assets/Demo/Mountain/1%20Source.png)
![2 Processing Mountain ](https://github.com/Radagrass-the-infinite/BlobDetection/blob/main/JavaBlobMain/src/Assets/Demo/Mountain/2%20Processing.png)
![3 Pre Prune Mountain](https://github.com/Radagrass-the-infinite/BlobDetection/blob/main/JavaBlobMain/src/Assets/Demo/Mountain/3%20Pre%20Prune.png)
![4 Post Prune Mountain](https://github.com/Radagrass-the-infinite/BlobDetection/blob/main/JavaBlobMain/src/Assets/Demo/Mountain/4%20Post%20Prune.png)

# Installing OpenCV on Mac for Java
Due to scarce documentation on JavaOpenCV here is a compilation of the steps Google Document to add the OpenCV dependency in Macs.
https://docs.google.com/document/d/185IR6hGh4MmqK4L1iKhF5Kx9na9wTka-MR_QAmxNoXg/edit

