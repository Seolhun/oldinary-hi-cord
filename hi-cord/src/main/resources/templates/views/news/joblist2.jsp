<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources/" var="RESOURCES" />
<spring:url value="/resources/template" var="template" />
<spring:url value="/music" var="music" />
<tag:layout tab="${target}">
	<!--=== Call To Action ===-->
	<div class="call-action-v1 bg-color-light">
		<div class="container">
			<div class="call-action-v1-box">
				<div class="call-action-v1-in">
					<p>Unify creative technology company providing key digital
						services and focused on helping our clients to build a successful
						business on web and mobile.</p>
				</div>
				<sec:authorize access="hasRole('SUPERADMIN')">
					<div class="call-action-v1-in inner-btn page-scroll">
						<a href="${music }/add"
							class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">Add
							New Music</a> <a href="${music }/get"
							class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">Get
							Music Rank</a>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>
	<!--=== End Call To Action ===-->
	<div class="container content-xs">
		<div class="row">
			<div class="col-sm-12">
				<div class="GNB_group_underline"></div>
				<div class="body_"></div>
				<div class="body_">
					<div class="body_left">
						<div class="breadcrumb font_14 font_limit">
							<a href="/"><i class="icon-home"></i></a> &gt; <a href="/insight">인사이트</a>
						</div>
						<div>

							<!--기사리스트시작-->
							<div class="news_list_ sub_list_ cb">
								<div>
									<h4
										class="news_list_full_size news_list_title default_font font_bold font_limit title_h3"
										title="" 애플 워치 판매, 그 어느 때보다 좋다"…애플 CEO 팀쿡">
										<a href="/insight/102471">"애플 워치 판매, 그 어느 때보다 좋다"…애플 CEO 팀
											쿡</a>
									</h4>
								</div>
								<div class="news_list_image fit_target fl">
									<img class="fit_image"
										src="http://files.idg.co.kr/itworld/image/avatar/article/2016/December/dylee1999@gmail.com/apple-watch-series-2-nike-waterproof-100681403-carousel.idge_.110x80.jpg?1481158254"
										style="display: none" />
								</div>
								<div class="news_list_has_thumb_size fl">

									<div class="news_body_summary font_14 cl">IDC가 애플 워치 매출이
										지난해에 비해 71% 감소한 것으로 추산한 후, 애플 CEO 팀 쿡은 실제 애플 워치는 잘 나가고 있다고
										반박했다.&nbsp; 쿡은 &quot;매출 성장은 차트에 나타나지 않았다&quot;며, &quot;사실 연
										...</div>
									<div class="of-h cb">
										<div class="news_list_source color_lightest_gray font_12">
											<a href="/t/55815/디지털 디바이스" class="color_lightest_gray">디지털
												디바이스</a> / <a href="/t/62071/웨어러블컴퓨팅"
												class="color_lightest_gray">웨어러블컴퓨팅</a>
										</div>
										<div
											class="news_list_time default_small_font color_lightest_gray">
											<i
												class="icon-time news_list_time_icon color_light_gray font_12 default_small_font"></i>
											2016.12.07
										</div>
									</div>
								</div>
							</div>
							<!--기사리스트끝-->
							<!--기사리스트시작-->
							<div class="news_list_ sub_list_ cb">
								<div>
									<h4
										class="news_list_full_size news_list_title default_font font_bold font_limit title_h3"
										title="2017년을 넘어 이어질 9가지 엔터프라이즈 기술 동향">
										<a href="/insight/102470">2017년을 넘어 이어질 9가지 엔터프라이즈 기술 동향</a>
									</h4>
								</div>
								<div class="news_list_image fit_target fl">
									<img class="fit_image"
										src="http://files.idg.co.kr/itworld/image/avatar/article/2016/December/just2kill@gmail.com/0_road-ahead-future-100678122-large.110x80.jpg?1481158254"
										style="display: none" />
								</div>
								<div class="news_list_has_thumb_size fl">

									<div class="news_body_summary font_14 cl">올해 엔터프라이즈 기술을 한
										단어로 요약하자면 명확성(clarity)이다. 컨테이너, 마이크로서비스, 클라우드 확장성, 데브옵스,
										애플리케이션 모니터링과 스트리밍 분석을 둘러싼 새로운 생태계가 일시적 유행이 아니라는 점은 밝혀졌다. 미래의
										기술로 ...</div>
									<div class="of-h cb">
										<div class="news_list_source color_lightest_gray font_12">
											<a href="/t/65210/BI/분석" class="color_lightest_gray">BI/분석</a>
											/ <a href="/t/62076/가상화" class="color_lightest_gray">가상화</a>
											/ <a href="/t/61023/개발자" class="color_lightest_gray">개발자</a>
											/ <a href="/t/35/데이터센터" class="color_lightest_gray">데이터센터</a>
											/ <a href="/t/69500/머신러닝" class="color_lightest_gray">머신러닝</a>
											/ <a href="/t/54649/빅 데이터" class="color_lightest_gray">빅
												데이터</a> / <a href="/t/63417/사물인터넷" class="color_lightest_gray">사물인터넷</a>
											/ <a href="/t/40/오피스＆협업" class="color_lightest_gray">오피스＆협업</a>
											/ <a href="/t/34/클라우드" class="color_lightest_gray">클라우드</a>
										</div>
										<div
											class="news_list_time default_small_font color_lightest_gray">
											<i
												class="icon-time news_list_time_icon color_light_gray font_12 default_small_font"></i>
											2016.12.07
										</div>
									</div>
								</div>
							</div>
							<!--기사리스트끝-->
							<!--기사리스트시작-->
							<div class="news_list_ sub_list_ cb">
								<div>
									<h4
										class="news_list_full_size news_list_title default_font font_bold font_limit title_h3"
										title="확산일로의 랜섬웨어, 서비스화가 주요 원인">
										<a href="/insight/102463">확산일로의 랜섬웨어, 서비스화가 주요 원인</a>
									</h4>
								</div>
								<div class="news_list_image fit_target fl">
									<img class="fit_image"
										src="http://files.idg.co.kr/itworld/image/avatar/article/2016/December/formeluv@hotmail.com/GettyImages-486885262.110x80.jpg?1481158254"
										style="display: none" />
								</div>
								<div class="news_list_has_thumb_size fl">

									<div class="news_body_summary font_14 cl">&quot;누구나 사이버
										범죄자가 될 수 있다! 아주 쉽다. 많은 돈이 필요하지 않다. 몇 시간만 투자해도 큰 돈을
										&#39;갈취&#39;할 수 있다! 코딩이나 소프트웨어 개발 방법을 학습하기 위해 몇 년이라는 시간을 낭비할
										필요 없다. 간단히 랜섬웨 ...</div>
									<div class="of-h cb">
										<div class="news_list_source color_lightest_gray font_12">
											<a href="/t/36/보안" class="color_lightest_gray">보안</a>
										</div>
										<div
											class="news_list_time default_small_font color_lightest_gray">
											<i
												class="icon-time news_list_time_icon color_light_gray font_12 default_small_font"></i>
											2016.12.07
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</tag:layout>