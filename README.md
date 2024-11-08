# java-convenience-store-precourse

## 기능 목록

### 1. 판매

| 기능        | 세부 내용                                                                                                                                |
|-----------|--------------------------------------------------------------------------------------------------------------------------------------|
| 1.1 재고 안내 | - 환영 인사와 함께 상품명, 가격, 프로모션 이름, 재고를 안내한다.<br/>- 만약 재고가 0개라면 “재고 없음”을 출력한다.                                                             |
| 1.2 구매    | - 사용자는 구매할 상품명과 수량을 입력할 수 있다.<br/>- 상품명, 수량은 하이픈(-)으로, 개별 상품은 대괄호([])로 묶어 쉼표(,)로 구분한다.                                               |
| 1.3 재고 차감 | - 고객이 상품을 구매하면, 결제된 수량만큼 해당 상품의 재고에서 차감한다.<br/>- 프로모션 기간 중이라면 프로모션 재고를 우선적으로 차감한다.<br/>- 프로모션 재고가 부족할 경우에는 일반 재고를 사용한다.              |
| 1.4 추가 구매 | - 영수증 출력 후 추가 구매를 진행할지 또는 종료할지를 여부를 입력받는다.<br/>- 고객이 “Y”를 입력할 경우 재고가 업데이트된 상품 목록을 확인 후 추가로 구매를 진행한다.<br/>- 고객이 “N”을 입력할 경우 구매를 종료한다. |

### 2. 입력 오류

| 기능                | 세부 내용                                                                                                                                                    |
|-------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| 2.1 입력 형식 오류      | - 구매할 상품과 수량 형식이 올바르지 않은 경우에 해당한다.<br/>- IllegalArgumentException을 발생시킨다.<br/>- 에러 메시지를 출력한 후 다시 입력을 받는다.<br/>- [ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요. |
| 2.2 존재하지 않는 상품 오류 | - 존재하지 않은 상품을 입력한 경우에 해당한다.<br/>- IllegalArgumentException을 발생시킨다.<br/>- 에러 메시지를 출력한 후 다시 입력을 받는다.<br/>- [ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.              |
| 2.3 재고 초과 오류      | - 구매 수량이 재고 수량을 초과한 경우에 해당한다.<br/>- IllegalArgumentException을 발생시킨다.<br/>- 에러 메시지를 출력한 후 다시 입력을 받는다.<br/>- [ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.   |
| 2.4 기타 오류         | - 기타 잘못된 입력의 경우에 해당한다.<br/>- IllegalArgumentException을 발생시킨다.<br/>- 에러 메시지를 출력한 후 다시 입력을 받는다.<br/>- [ERROR] 잘못된 입력입니다. 다시 입력해 주세요.                       |

### 3. 프로모션

| 기능           | 세부 내용                                                                                                                                                                                  |
|--------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 3.1 프로모션 적용  | - 오늘 날짜가 프로모션 기간 내에 포함된 경우 할인을 적용한다.<br/>- 프로모션은 N개 구매 시 1개 무료 증정의 형태로 진행된다.<br/>- 한 상품에는 하나의 프로모션만 적용된다.<br/>- 프로모션 혜택은 프로모션 재고 내에서만 적용할 수 있다.                                        |
| 3.2 프로모션 추가  | - 프로모션 적용 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내하고 추가 여부를 입력받는다.<br/>- 고객이 “Y”를 입력한 경우 증정 받을 수 있는 상품을 추가한다.<br/>- 고객이 “N”을 입력한 경우 증정 받을 수 있는 상품을 추가하지 않는다.     |
| 3.3 프로모션 미적용 | - 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제하게 됨을 안내하고 결제 여부를 입력받는다.<br/>- 고객이 “Y”를 입력한 경우 일부 수량에 대해 정가로 결제한다.<br/>- 고객이 “N”을 입력한 경우 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다. |

### 4. 멤버십

| 기능         | 세부 내용                                                                                                                                                                          |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 4.1 멤버십 할인 | - 멤버십 회원은 프로모션 적용 후 남은 금액의 30%를 할인받을 수 있다.<br/>- 멤버십 할인의 최대 한도는 8,000원이다.<br/>- 멤버십 할인 여부를 입력 받는다.<br/>- 고객이 “Y”를 입력한 경우 멤버십 할인을 적용한다.<br/>- 고객이 “N”을 입력한 경우 멤버십 할인을 적용하지 않는다. |

### 5. 영수증

| 기능              | 세부 내용                                                                                                                                          |
|-----------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| 5.1 구매 상품 내역 출력 | - 구매한 상품명, 수량, 가격을 출력한다.                                                                                                                       |
| 5.2 증정 상품 내역 출력 | - 프로모션에 따라 무료로 제공된 증정 상품 목록을 출력한다.                                                                                                             |
| 5.3 금액 정보 출력    | - 총구매액, 행사할인, 멤버십 할인, 내실돈을 출력한다.<br/>- 총구매액: 구매한 상품의 총 수량과 금액<br/>- 행사할인: 프로모션에 의해 할인된 금액<br/>- 멤버십 할인: 멤버십에 의해 추가로 할인된 금액<br/>- 내실돈: 최종 결제 금액 |
