# BlobDetection
This repository holds a Custom Blob Detection Algorithm using OpenCV written in Java. This algorithm only uses OpenCV for image display and pixel color reading from images; Inspired by the JavaScript PS5 tutorial by the [Coding Train]([url](https://www.youtube.com/watch?v=ce-2l2wRqO8)).

# How the Algorithm Works
**What Are Blobs?**
A Blob is a group of pixels that meet are similar in characteristics such as: Color, Distance, Shape, Convexity. This algorithm utilizes RGB Color and Distance.

**Blob Detection Logic**
The algorithm has three main steps:
1. Identify pixels that are similar in color.
2. Merge similar colored pixels that are close together to form a Blob.
3. Merge neighboring Blobs to create bounding boxes.


Optimizations
- Decimation
- Artifact Elimination

UI Structure

# Future Capabilities
HSV Color Filters

Contours for Multi-colored

Camera Homography

#**Examples**
Below are the three steps with various cases:
![1 Source Mountain ](https://github.com/Radagrass-the-infinite/BlobDetection/blob/main/JavaBlobMain/src/Assets/Demo/Mountain/1%20Source.png)
![2 Processing Mountain ](https://github.com/Radagrass-the-infinite/BlobDetection/blob/main/JavaBlobMain/src/Assets/Demo/Mountain/2%20Processing.png)
![3 Pre Prune Mountain](https://github.com/Radagrass-the-infinite/BlobDetection/blob/main/JavaBlobMain/src/Assets/Demo/Mountain/3%20Pre%20Prune.png)
![4 Post Prune Mountain](https://github.com/Radagrass-the-infinite/BlobDetection/blob/main/JavaBlobMain/src/Assets/Demo/Mountain/4%20Post%20Prune.png)

Due to scarce documentation on JavaOpenCV here is a compilation of the steps [Google Document]([url](https://docs.google.com/document/d/185IR6hGh4MmqK4L1iKhF5Kx9na9wTka-MR_QAmxNoXg/edit)) to add the OpenCV dependency in Macs.

