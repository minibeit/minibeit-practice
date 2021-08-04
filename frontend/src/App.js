import React from 'react';
import { Route, Switch } from 'react-router-dom';
import { createGlobalStyle } from 'styled-components';
import ApplyHome from './pages/ApplyHome';
import ExplainPage from './pages/ExplainPage';
import FeedDetail from './pages/FeedDetail';
import FeedEdit from './pages/FeedEdit';
import FeedList from './pages/FeedList';
import FeedNew from './pages/FeedNew';
import Login from './pages/Login';
import Main from './pages/Main';
import Mypage from './pages/Mypage';
import MypageEdit from './pages/MypageEdit';
import Signup from './pages/Signup';

function App() {
  return (
    <>
      <GlobalStyle />
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/signup" component={Signup} />
        <Route path="/mypage/edit" component={MypageEdit}/>
        <Route path="/mypage" component={Mypage} />
        <Route path="/feedList/:category/:postPk/feedEdit"  component={FeedEdit} />
        <Route path="/feedList/:category/feedNew"  component={FeedNew} />
        <Route path="/feedList/:category/:postPk" exact component={FeedDetail} />
        <Route path="/feedList/:category" exact component={FeedList} />
        <Route path="/applyHome" component={ApplyHome} />
        <Route path="/explainPage/:category" component={ExplainPage} />
        <Route path="/" exact component={Main} />
      </Switch>
    </>
  );
}

export default App;

const GlobalStyle = createGlobalStyle`
  * {
    padding: 0px;
    margin: 0px;
    /* box-sizing: border-box; */
    font-family: "애플 SD 산돌고딕 Neo", "Apple SD Gothic Neo", "Malgun Gothic", "arial sans-serif";
  }
`;
