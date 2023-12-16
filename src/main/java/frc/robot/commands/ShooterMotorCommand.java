// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;
import static frc.robot.Constants.ShooterConstants.*;

public class ShooterMotorCommand extends Command {
  private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
  double shooterSpeed;
  Timer timer;

  /** Creates a new ShooterMotorCommand. */
  public ShooterMotorCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooterSpeed = shooterSpeed;
    this.addRequirements(shooter);
    this.timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.shooter.setShooterSpeed(shooterSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.shooter.setShooterSpeed(0);
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    }
}