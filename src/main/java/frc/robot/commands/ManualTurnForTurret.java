// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSubsystem;

public class ManualTurnForTurret extends Command {
  private final TurretSubsystem turret = TurretSubsystem.getInstance();
  private int vector;
  private double speed;

  public ManualTurnForTurret(int vector, double speed) {
    this.vector = vector; // vector is 1 or -1 for right or left
    this.speed = speed; // the speed of the motor
  }

  @Override
  public void initialize() {
    turret.setSpeed(speed * vector);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    turret.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
