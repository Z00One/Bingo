// 구조적 언어 관점
const num_of_user = 2;
const bingo_size = 5;
const range_of_int = bingo_size * bingo_size;

// 중복되지 않는 난수 25개를 원소로 가지는 1차원 배열 반환하는 함수
function get_random_array(length, range_of_value) {

  let random_array = [];

  for (let i = 0; i < length; i++) {
    randomValue = Math.floor(Math.random() * range_of_value) + 1;
    // 중복 확인
    for (let j = 0; j < range_of_value; j++) {
      if (random_array[j] == randomValue) {
        j = -1
        // floor - 소숫점 버리기
        randomValue = Math.floor(Math.random() * range_of_value) + 1;
      }
    }
    // push - append
    random_array.push(randomValue);
  }
  return random_array;
}

// 중복되지 않는 25개의 값을 담은 1차원 배열 담은 2차원 배열을 반환
function get_bingo_values(num_of_bingo) {

  let bingo_array = [];
  // 배열의 길이가 인자값만큼만 되도록 반복
  for (let i = 0; bingo_array.length < num_of_bingo; i++) {
    // 값이 아예 없을 때
    if (bingo_array.length == 0) {
      bingo_array.push(get_random_array(bingo_size * bingo_size, range_of_int));
      i = -1;
      continue;
    }

    let flag = true;
    let random_array = get_random_array(bingo_size * bingo_size, range_of_int);

    // 1차원 배열의 값이 하나라도 다르면 다른 것이므로 push
    for (let j = 0; j < random_array.length && flag; j++) {
      if (bingo_array[i][j] != random_array[j]) {
        bingo_array.push(random_array);
        flag = false;
      }
    }
    // 같은 빙고인 경우
    if (flag) {
      i = -1;
    }
  }
  return bingo_array;
}

// 5 X 5 빙고 배열로 구현하기
function get_bingo(array) {
  // 인자값으로 들어온 1차원 배열 2차원 배열화

  // 5 X 5 2차원 배열 선언
  let bingo = new Array(bingo_size); // 행
  for (let i = 0; i < bingo.length; i++) { // 열
    bingo[i] = new Array(bingo_size);
  }
  // 값 넣어주기
  for (let i = 0; i < array.length; i++) {
    let col = Math.floor(i / bingo_size);
    let row = i % bingo_size;
    bingo[col][row] = array[i];
  }

  return bingo;
}

// 시각화 해주기
// function visualization(array) {
//   // 행
//   for (let i = 0; i < bingo_size; i++)
//     // 빙고 종이 선택
//     for (let k = 0; k < bingo_size; k++) {
//       const td = document.getElementsByTagName('td').item(k);
//       td.innerText = array[k];
//     };
// };

function visualization(array) {
  // 테이블에 행 만들기
  for (let sheet = 0; sheet < num_of_user; sheet++) {
    let id_table = "table" + sheet;

    // 행
    for (let col = 0; col < bingo_size; col++) {
      let table = document.getElementById(id_table);

      // 해당 table에 행 추가하기
      let id_tr = id_table + "tr" + col;
      let tr = document.createElement('tr');
      tr.setAttribute('id', id_tr);
      table.appendChild(tr);

      // 열
      for (let row = 0; row < bingo_size; row++) {
        let tr = document.getElementById(id_tr);

        // 해당 행에 열 추가하기
        let id_td = id_tr + "td" + row;
        let td = document.createElement('td');
        td.setAttribute('id', id_td);
        tr.appendChild(td);
        td.innerText = array[sheet][col][row];
      };
    }
  }
};
// 값 입력 받기

// 인자로 받은 값에 대하여 색칠 하기
function check(array, input_value) {
  // 해당되는 값 찾기
  for (let table = 0; table < array.length; table++) {
    const id_t = 'table' + table;
    console.log(id_t);
    for (let tr = 0; tr < array[table].length; tr++) {
      const id_tr = 'tr' + tr;
      console.log(id_tr);
      for (let td = 0; td < array[table][tr].length; td++) {
        // 2차원 배열에 해당되는 td의 id 찾기
        let check_id = id_t + id_tr + 'td' + td;
        // 값 가져오기
        let check_value = document.getElementById(check_id)
        // 비교
        if (array[table][tr][td] == input_value) {
          check_value.className = 'checked';
        };
      };
    };
  };
}

let bingo = get_bingo_values(2);
bingo = [get_bingo(bingo[0]), get_bingo(bingo[1])];
console.log(bingo);
visualization(bingo);


// 값 입력받아 처리 - 노션 자료
document.getElementById("form").onsubmit = function () {
  let sc = document.getElementById("inputValue")
  check(bingo, sc.value);
  sc.value = "";
  // form 의 action을 실행하지 않기 위해
  return false;
}

// 내 빙고 - 숫자 다 보임
// 상대방에게 보여줄 빙고 - 숫자 안보임

// 1. 숫자 매핑하지 않고 시각화
// -> tr, td를 생성하는 방식으로
//    행 , 열에 해당하는 부분을 시각화하는게 좋을듯

// 2. 빙고 체크
// -> 1에서 행, 열에 해당하는 태그를 알 수 있다면
// 자바에서 만든 내용 그대로 포팅하면 될 듯


  // console.log(get_(array)[1]);
// }
  // 2차원 배열에 해당하는 table에 값 넣어주기

// 입력되는 값에 해당하는 값 색칠

// 입력되는 값 매핑

// 빙고 체크