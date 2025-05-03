# proven-requester

# access-struct

1. Proven-Requester
2. Proven-Accesser

Requester(Main Server) -> Accesser(User Server)

# Command(Request)

1. Order Example (Method: POST) v1, v2, v3
```json
{
    "symbol": "BTCUSDT",
    "leverage": 8,
    "marginType": "ISOLATED",
    "side": "LONG",
    "atr": 0.0,
    "close": 97482.2,
    "min_qty": 0.1
}
```

Types
```
symbol: String                          # 매매 심볼
leverage: Integer                       # 매매 레버리지
marginType: String # ISOLATED, CROSS    # 마진 타입
side: String # LONG, SHORT              # 매매 사이드
atr: Double                             # 변동성
close: Double                           # 전 종가
min_qty: Double                         # 최소 진입 Quantity
```

2. Save Information (Method: POST)
```json 
{}
```