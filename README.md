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

Below are the three steps with various cases:

Optimizations
- Decimation
- Artifact Elimination

UI Structure

# Future Capabilities
HSV Color Filters

Contours for Multi-colored

Camera Homography

Here is also some documentation on how to add OpenCV dependency in Macs as JavaOpenCV library has scarce documentation.
https://docs.google.com/document/d/185IR6hGh4MmqK4L1iKhF5Kx9na9wTka-MR_QAmxNoXg/edit
