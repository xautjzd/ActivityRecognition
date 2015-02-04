package edu.xautjzd.activityrecognition.decisiontree;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attributes")
public class Attribute {
	@Id 
	@GeneratedValue
	private Integer Id;      // 自增主鍵
	@Column(name = "Person")
	private String Person;  // 采楊者
	private String Action;  // 動作 
	
	private Double X_Average;       // 均值
	private Double Y_Average;
	private Double Z_Average;
	
	private Double X_Deviation;     // 方差
	private Double Y_Deviation;
	private Double Z_Deviation;
	
	private Double XY_Correlation;  // 相關係數
	private Double YZ_Correlation;
	private Double XZ_Correlation;
	
	private Double X_Skewness;      //偏度
	private Double Y_Skewness;
	private Double Z_Skewness;
	
	private Double X_Kurtosis;     // 峰度
	private Double Y_Kurtosis;
	private Double Z_Kurtosis;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getPerson() {
		return Person;
	}
	public void setPerson(String person) {
		Person = person;
	}
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public Double getX_Average() {
		return X_Average;
	}
	public void setX_Average(Double x_Average) {
		X_Average = x_Average;
	}
	public Double getY_Average() {
		return Y_Average;
	}
	public void setY_Average(Double y_Average) {
		Y_Average = y_Average;
	}
	public Double getZ_Average() {
		return Z_Average;
	}
	public void setZ_Average(Double z_Average) {
		Z_Average = z_Average;
	}
	public Double getX_Deviation() {
		return X_Deviation;
	}
	public void setX_Deviation(Double x_Deviation) {
		X_Deviation = x_Deviation;
	}
	public Double getY_Deviation() {
		return Y_Deviation;
	}
	public void setY_Deviation(Double y_Deviation) {
		Y_Deviation = y_Deviation;
	}
	public Double getZ_Deviation() {
		return Z_Deviation;
	}
	public void setZ_Deviation(Double z_Deviation) {
		Z_Deviation = z_Deviation;
	}
	public Double getXY_Correlation() {
		return XY_Correlation;
	}
	public void setXY_Correlation(Double xY_Correlation) {
		XY_Correlation = xY_Correlation;
	}
	public Double getYZ_Correlation() {
		return YZ_Correlation;
	}
	public void setYZ_Correlation(Double yZ_Correlation) {
		YZ_Correlation = yZ_Correlation;
	}
	public Double getXZ_Correlation() {
		return XZ_Correlation;
	}
	public void setXZ_Correlation(Double xZ_Correlation) {
		XZ_Correlation = xZ_Correlation;
	}
	public Double getX_Skewness() {
		return X_Skewness;
	}
	public void setX_Skewness(Double x_Skewness) {
		X_Skewness = x_Skewness;
	}
	public Double getY_Skewness() {
		return Y_Skewness;
	}
	public void setY_Skewness(Double y_Skewness) {
		Y_Skewness = y_Skewness;
	}
	public Double getZ_Skewness() {
		return Z_Skewness;
	}
	public void setZ_Skewness(Double z_Skewness) {
		Z_Skewness = z_Skewness;
	}
	public Double getX_Kurtosis() {
		return X_Kurtosis;
	}
	public void setX_Kurtosis(Double x_Kurtosis) {
		X_Kurtosis = x_Kurtosis;
	}
	public Double getY_Kurtosis() {
		return Y_Kurtosis;
	}
	public void setY_Kurtosis(Double y_Kurtosis) {
		Y_Kurtosis = y_Kurtosis;
	}
	public Double getZ_Kurtosis() {
		return Z_Kurtosis;
	}
	public void setZ_Kurtosis(Double z_Kurtosis) {
		Z_Kurtosis = z_Kurtosis;
	}
}
