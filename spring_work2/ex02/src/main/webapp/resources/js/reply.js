console.log("reply......");

/*
(function() {} => 즉시실행함수다.
return 타입은 객체이다.
replyService에 add 라는 객체가 들어간다.
add 객체에는 프로퍼티로 add()함수가 들어가있다.
*/
var replyService = (function() {

	// 댓글 추가
	function add(reply, callback) {
		console.log("reply add.........");
		
		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply), // 자바스크립트 객체를 JSON 문자열로 변환
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}

	// 리스트 조회
	function getList(param, callback, error) {

		var bno = param.bno;
		var page = param.page || 1;

		$.getJSON("/replies/pages/" + bno + "/" + page,
			function(data) {
				if (callback) {
						callback(data);
				}
			}).fail(function(xhr, status, err) {
				if (error) {
						error();
				}
			});
	}

	// 삭제
	function remove(rno, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}

	// 수정
	function update(reply, callback, error) {

		console.log("RNO: " + reply.rno);

		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}

	// 상세 조회
	function get(rno, callback, error) {

		$.get("/replies/" + rno, function(result) {

			if (callback) {
				callback(result);
			}

		}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}
		});
	}
	
	return {
		add: add,
		getList: getList,
		remove: remove,
		update: update,
		get: get
	};

	
})();