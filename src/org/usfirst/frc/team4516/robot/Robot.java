package org.usfirst.frc.team4516.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
	RobotDrive myRobot;
	Joystick leftStick;
	Joystick rightStick;
	Joystick launchStick;
	SpeedController loadingMotor;
	SpeedController sendMotor;
	SpeedController launchMotor;
	SpeedController armMotor;

	public Robot() {
		myRobot = new RobotDrive(2, 3, 0, 1);

		myRobot.setExpiration(0.1);
		myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		myRobot.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		myRobot.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		launchStick = new Joystick(2);

		loadingMotor = new VictorSP(4);
		sendMotor = new VictorSP(5);
		launchMotor = new Victor(6);
		armMotor = new Victor(7);

		SmartDashboard.putNumber("Launch Motor Status", launchMotor.get());

	}

	public void autonomous() {
	
	}

	public void operatorControl() {
		myRobot.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			myRobot.tankDrive(leftStick, rightStick, true);
			Timer.delay(0.005);

			loadBall();
			sendBall();
			launchBall();
		}

	}

	public void loadBall() {
		if (launchStick.getRawButton(2))
			loadingMotor.set(0.7);
		else
			loadingMotor.set(0.0);
	}

	public void sendBall() {
		if (launchStick.getRawButton(3))
			sendMotor.set(0.7);
		else
			sendMotor.set(0);
	}

	public void launchBall() {
		if (launchStick.getRawButton(1))
			launchMotor.set(0.7);
		else
			launchMotor.set(0);
	}

	public void autoLaunch() {
		if (launchStick.getRawButton(999)) {
			launchMotor.set(0.7);
			Timer.delay(1.5);
			sendMotor.set(0.7);
			Timer.delay(1.5);
			launchMotor.set(0);
			sendMotor.set(0);
		}
	}

}