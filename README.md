# いきものSNS

- 目次
  - 制作のきっかけ(ターゲット)
  - 制作背景
  - 内容
  - 苦労した点
  - 5か月間の振り返り  

  

## 制作のきっかけ(ターゲット)
  わたしは色々などうぶつが好きです。
  
  疲れた時に、ふと犬や猫、鳥などの生き物の画像・投稿をSNSで見て一時の癒しを得ています。

  昨今、さまざまなSNSが普及している中で、X(旧：Twitter)やinstagramではインプレッションに応じて収益化が行われており、"インプレッション稼ぎ"の投稿が多く、純粋ないきものの投稿が見れずもやもやすることが実体験としてありました。
  
  自分自身は生き物の投稿を見たいだけなのに、人間の承認欲求や自己顕示欲にまみれた投稿ばかりが目に入るため、逆に疲れてしまうことがあります。

  そんなときに、人間に関する投稿ができない(禁止する)、「人間以外に関する投稿のみできる」SNSがあればいいのでは？と思い立って制作に取り組みました。 



## 制作背景
  訓練を通じてたくさんのプログラミング言語を学びました。
  
  機能の実装方法などは学んだものの、具体的に1つのプロジェクトを制作する場合どのようにシステムとして連携させて動かすのか、という部分の理解が足りていないと思いました。

  今回の制作実習では、学んだことを広く用いてプロジェクトを実装することで知識・理解に落とし込むことを主軸において制作しました。
  
  特別な方法やAPIは用いていませんが、自分なりに取り組んでみました。


## 実装内容
  - ### 使用言語
    Pythonも使用したかったのですが力尽きました。  
    ゆくゆくはPythonを組み込んだアプリケーションを作成したいです。
    
    - Java
    - Servlet / JSP
    - SQL
    - HTML
    - CSS
    - javaScript


  - ### DB構築(ER図)

    自分の理解している範囲で正規化を行いました。
    
    ER図は以下の通りです。(途中で力尽きたため、正しい正規化が行われていない部分があります…)
    
    ![er](https://github.com/user-attachments/assets/c067b8fd-bfac-4438-92cd-5232322fc024)

  - ### 機能要件  
    実装にたどり着けなった機能もありますが、想定していた要件を記述しています。  
    
    - #### ユーザーの新規登録
      
      任意のユーザー名(表示名)、メールアドレス、パスワードを設定  
        ⇒ セキュリティ性を保持するため、パスワードはハッシュ化してDBに格納
     
    - #### ログイン

      登録済みのメールアドレス・パスワードを入力してログイン
     
    - #### ポスト(画像の有無は任意)

      ポストしたい文章を入力  
      アップロードしたい画像を選択(任意)  
      ポスト内容に沿ったカテゴリ選択(プルダウンから選択)
      　⇒ いきものカテゴリを選択させることで、人間に関する投稿ができないように抑止
      
       
    - #### リアクション機能

      タイムラインに表示されている投稿に対して絵文字でリアクション(プルダウンから選択)  
      　⇒ 不快なリアクションを送れないように、リアクションできる絵文字はDB上で登録されているもののみ選択可
      
      リアクションの絵文字表示にはTwemojiを使用  
      　⇒ ユーザーの環境(ブラウザ・OS)に左右されないよう絵文字の表示を統一させるため


## 苦労した点
  
  - #### DBの正規化

    DBの正規化に注力したところ、javaとの連携がややこしくなってしまい詳細設計・実装工程で時間を多く割かれてしまいました。

    「データの整合性を追求する　⇔　管理も難しくなる」　ということを実体験で学びました。  

  
  - #### 画像ファイルのDB連携

    サーバー上に画像を保存することはできたものの、DBに画像パスをvarchar型で格納し、それを取り出して表示させることが難しかったです。

    単体テストでは動作したものの、他の機能と結合させるとうまく動かず、苦労した点です。  


##  5か月間の振り返り
  
  この5か月間で"webアプリケーションエンジニアの基礎中の基礎知識"を学ぶことができてよかったです。
  
  まだ自分自身でもWeb系、業務系、基幹系といった "どのエンジニアに向いているのか" は分かりませんが、今後の業務で必ず生きてくる知識だと思うので、就職後も積極的に自己学習やキャッチアップを進めていきたいです。
  
  就活を通じて、世間一般では話されていないような「未経験エンジニアの現実」の部分も実体験でき、いい意味で勉強になりました。
  

  
  社会人になってからは不特定多数の方々と交流できる機会がまったくなかったので、
  クラスのみなさんと関われて新鮮で貴重な経験ができたと思います。

  みなさんの今後の活躍を応援しています^^!  
  

    
  
  本当にお世話になりました。ありがとうございました。

  
