$(document).ready(function() {
	var boardType=$("#boardType").val();
	
	BoardList();
	function BoardList(){
		//동적 환자 리스트 DataTable
	    $("#boardJsGrid").jsGrid({
	    	height : "600",
	    	width : "100%",
	    	selecting : true,
	    	autoload : true,
	    	filtering: true,
	        editing: true,
	        sorting : true,
        	paging : true,
            pagerContainer: null,
            pageIndex: 1,
            pageSize: 10,
            pageButtonCount: 15,
            pagerFormat: "Pages: {prev} {pages} {next} : {pageIndex} of {pageCount}",
            pagePrevText: "Prev",
            pageNextText: "Next",
            pageNavigatorNextText: "...",
            pageNavigatorPrevText: "...",
	        noDataContent : "현재 게시물이 없습니다.",

	        rowClick: function(args) {
	        	var item = args.item;
        		
        		//선택한 인원 체크하기.
        		var $row = this.rowByItem(args.item);
        		//클릭되어있지 않으면 false, 클릭된것을 다시 누르면 true.
        		if($row.hasClass("highlighted")){
        			$row.toggleClass("highlighted");
        		} else {
        			$(".highlighted").toggleClass("highlighted");
        			$row.toggleClass("highlighted");
        		}
	        },
	    	controller : {
	    		loadData : function(filter) {
	                 var d = $.Deferred();
	                 $.ajax({
	    				type : "GET",
	    				contentType : "application/json; charset=utf-8",
	    				url : root+"/board/"+boardType+"/list-json", 
	    				dataType : "json",
	    				beforeSend: function(xhr) {
						    xhr.setRequestHeader("Accept", "application/json");
						    xhr.setRequestHeader("Content-Type", "application/json");
						    xhr.setRequestHeader(csrfHeader, csrfToken);
	    				},
	    			}).done(function (response) {
	                    response = $.grep(response, function (item) {
                            return (!filter.subject || (item.subject === null ? "" : item.subject).toLowerCase().indexOf(filter.subject.toLowerCase()) > -1)
                                && (!filter.id || (item.id === null ? "" : item.id).toLowerCase().indexOf(filter.id.toLowerCase()) > -1)
                                && (!filter.boardType || (item.boardType === null ? "" : item.boardType).toLowerCase().indexOf(filter.boardType.toLowerCase()) > -1)
                                && (!filter.createdBy || (item.createdBy === null ? "" : item.createdBy).toLowerCase().indexOf(filter.createdBy.toLowerCase()) > -1)
                                && (!filter.createdDate || (item.createdDate === null ? "" : item.createdDate).toLowerCase().indexOf(filter.createdDate.toLowerCase()) > -1);
	                    });
	                    d.resolve(response);
	    			});
	    			return d.promise();
    			}
	    	},
	    	fields : [{
	    		name : "id",
	    		title: "번호",
	    		type : "text",
	    		width : 30,
	    	}, {
	    		name : "subject",
	    		title: "제목",
	    		type : "text",
	    		width : 200,
	    		align : "center"
	    	},  {
	    		name : "createdBy",
	    		title: "글쓴이",
	    		type : "text",
	    		width : 60,
	    		align : "center",
	    	},  {
	    		name : "createdDate",
	    		title: "등록일",
	    		type : "text",
	    		width : 60,
	    		align : "center"
//	    		itemTemplate: function(value, item) {
//	                return moment(value).format('YYYY-MM-DD HH:mm');
//	    		}
	    	},  {
	    		name : "hits",
	    		title: "조회수",
	    		type : "date",
	    		width : 60,
	    		align : "center",

	    	}]
		});
	}
});