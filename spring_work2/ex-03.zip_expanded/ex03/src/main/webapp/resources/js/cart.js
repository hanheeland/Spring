/**
 * cart.js
 */
 
 $(document).ready(function() {
    $("form[name='form1']").submit(function(event) {
        // 기본 폼 제출 방식을 막기
        event.preventDefault();

        // 폼 데이터를 가져오기
        var formData = $(this).serialize(); // 폼의 모든 데이터를 문자열로 직렬화

        $.ajax({
            type: "POST",
            url: $(this).attr('action'), // form의 action 속성 값 사용
            data: formData,
            success: function(response) {
                alert('상품이 장바구니에 추가되었습니다.');
            },
            error: function(error) {
                alert('상품을 추가하는 데 문제가 발생했습니다.');
            }
        });
    });
});
 