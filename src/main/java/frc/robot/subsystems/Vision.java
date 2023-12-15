// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
 
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.units.Distance;
import frc.robot.Constants;
/** Add your docs here. */
public class Vision {
    private AprilTagFieldLayout kTagLayout = AprilTagFields.k2023ChargedUp.loadAprilTagLayoutField();
    private final PhotonCamera Camera;
    private final PhotonPoseEstimator photonEstimator;

    
    public Vision(){
        Camera = new PhotonCamera(Constants.VisionConstants.FRONT_CAMERA_NAME);
        photonEstimator = new PhotonPoseEstimator(kTagLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, Camera, Constants.VisionConstants.kRobotToCam);
        //photonEstimator = new PhotonPoseEstimator(kTagLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, Camera, Constants.VisionConstants.kRobotToCam);
        photonEstimator.setMultiTagFallbackStrategy(PoseStrategy.LOWEST_AMBIGUITY);

    }

    public enum PoseStrategy{
        MULTI_TAG_PNP_ON_COPROCESSOR,
        LOWEST_AMBIGUITY
    }
}