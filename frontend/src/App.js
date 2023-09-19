/** @jsxImportSource @emotion/react */
import 'twin.macro';
import axios from 'axios';
import React, { useEffect, useState } from 'react';

function TailDiv() {
  return <div tw="bg-black">hello</div>;
}

function App() {
  const [value, setValue] = useState('');
  useEffect(() => {
    axios
      .get('/api/hi')
      .then((res) => {
        setValue(res.data);
      })
      .catch((error) => {
        if (error.response) {
          // 서버가 응답하지 않거나 오류 응답을 반환한 경우
          console.error(
            '서버 응답 오류:',
            error.response.status,
            error.response.data,
          );
        } else if (error.request) {
          // 요청이 서버에 도달하지 못한 경우
          console.error('서버에 요청이 도달하지 않았습니다.');
        } else {
          // 요청을 설정하는 중에 오류가 발생한 경우
          console.error('요청 설정 오류:', error.message);
        }
      }, []);
  });

  return (
    <div>
      <TailDiv />
      <div>Hello!</div>
      <p>{value}</p>
      <p>hi!!</p>
    </div>
  );
}

export default App;
