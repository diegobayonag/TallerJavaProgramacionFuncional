package edu.semillero;

public class Vehicle {

	private String _brand;
	private String _series;
	private int _model;
	private double _price;
	private String _color;
		
	
	public Vehicle(String _brand, String _series, int _model, double _price, String _color) {
		super();
		this._brand = _brand;
		this._series = _series;
		this._model = _model;		
		this._price = _price;		
		this._color = _color;
		
	}

	public String get_brand() {
		return _brand;
	}
	public void set_brand(String _brand) {
		this._brand = _brand;
	}
	public String get_series() {
		return _series;
	}
	public void set_series(String _series) {
		this._series = _series;
	}
	public int get_model() {
		return _model;
	}
	public void set_model(int _model) {
		this._model = _model;
	}
	public String get_color() {
		return _color;
	}
	public void set_color(String _color) {
		this._color = _color;
	}
	
	public double get_price() {
		return _price;
	}

	public void set_price(double _price) {
		this._price = _price;
	}
	
	
	
}
