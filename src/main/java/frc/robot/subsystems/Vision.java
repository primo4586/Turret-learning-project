// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.List;
import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.TargetCorner;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import static frc.robot.Constants.VisionConstants.*;
import frc.robot.Robot;

public class Vision {
    private AprilTagFieldLayout kTagLayout = AprilTagFields.k2023ChargedUp.loadAprilTagLayoutField();
    private final PhotonCamera camera;
    private final PhotonPoseEstimator photonEstimator;
    double lastEstTimestamp = 0;
    double distanceFromTarget;
    double currentXPosition;
    double currentYPosition;
    double sideA;
    double sideB;
    double sideC;
    double angle;

    public Vision() {
        camera = new PhotonCamera(FRONT_CAMERA_NAME); // creating camera
        photonEstimator = new PhotonPoseEstimator(kTagLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, camera,
               kRobotToCam);
        photonEstimator.setMultiTagFallbackStrategy(PoseStrategy.LOWEST_AMBIGUITY);

    }
    // 1. getting position:
    public Optional<EstimatedRobotPose> getEstimatedGlobalPose() {
        var visionEst = photonEstimator.update();
        double latestTimestamp = camera.getLatestResult().getTimestampSeconds();
        boolean newResult = Math.abs(latestTimestamp - lastEstTimestamp) > 1e-5;
        if (newResult)
            lastEstTimestamp = latestTimestamp;
        return visionEst;
    }

    // 2. The latest estimated robot pose on the field from vision data, location
    // update:
    public PhotonPipelineResult getLatestResult() {
        return camera.getLatestResult();
    }
    
    public Matrix<N3, N1> getEstimationStdDevs(Pose2d estimatedPose) {
        var estStdDevs = kSingleTagStdDevs;
        var targets = getLatestResult().getTargets();
        int numTags = 0;
        double avgDist = 0;
        for (var tgt : targets) {
            var tagPose = photonEstimator.getFieldTags().getTagPose(tgt.getFiducialId());
            if (tagPose.isEmpty())
                continue;
            numTags++;
            avgDist += tagPose.get().toPose2d().getTranslation().getDistance(estimatedPose.getTranslation());
        }
        if (numTags == 0)
            return estStdDevs;
        avgDist /= numTags;
        // Decrease std devs if multiple targets are visible
        if (numTags > 1)
            estStdDevs = kMultiTagStdDevs;
        // Increase std devs based on (average) distance
        if (numTags == 1 && avgDist > 4)
            estStdDevs = VecBuilder.fill(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        else
            estStdDevs = estStdDevs.times(1 + (avgDist * avgDist / 30));

        return estStdDevs;
    }

    public double DistanceFromTarget(){
        return PhotonUtils.getDistanceToPose(getEstimatedGlobalPose().get().estimatedPose.toPose2d(), target);
        
    }

    public Rotation2d GetAngleFromTarget(){
        return PhotonUtils.getYawToPose(getEstimatedGlobalPose().get().estimatedPose.toPose2d() , target);
        

    }
    
}
