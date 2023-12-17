// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;

public class MoveTurretToDegree extends InstantCommand {
  private final TurretSubsystem turret = TurretSubsystem.getInstance();
  private double degree;

  public MoveTurretToDegree(double degree) {
    this.degree = degree;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turret.putTurretInAngle(degree);
  }
}