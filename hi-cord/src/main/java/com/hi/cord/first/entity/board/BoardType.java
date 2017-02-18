package com.hi.cord.first.entity.board;

public enum BoardType {
	//향후 더 추가예정 /PathVariable에 쓸 수 있는 객체들을 사용한다.
	Notice("notice"),
	QnA("qna"),
	Freeboard("freeboard");
	
	private String type;
	
	private BoardType(String type){
		this.type=type;
	}
	
	public String getBoardName(){
		return type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString(){
		return this.type;
	}

	public String getName(){
		return this.name();
	}
}
