# 学习GitHub的一些笔记

## 基本操作

1. 安装:horse:

2. 设置账户   config --global

   - 设置用户名 

     ```powershell
     $ git config --global user.name "Your Name"
     ```

   - 设置邮箱

     ```powershell
     $ git config --global user.email "email@email.com"
     ```

   - 因为Git是分布式版本控制系统，所以，每个机器都必须自报家门：你的名字和Email地址。

3. 创建本地仓库

   - 创建空目录

     ```powershell
     $ mkdir fileName        		#新建名为fileName的空文件夹
     $ cd fileName					#移动到fileName目录下
     $ pwd							#显示当前目录，
     /c/Users/lixianda/fileName      #这个仓库位于/c/Users/lixianda/fileName。
     ```

   - 将此目录变为Git仓库

     ```powershell
     $ git init
     Initialized empty Git repository in /c/Users/lixianda/fileName
     ```

4. 添加文件到本地仓库

   - 将需要关联的文件放入仓库fileName目录下 例如: readme.md

     ```powershell
     $ git add readme.md			#放入到目录下，再用add告诉Git仓库添加该文件
     $ git add .                 #一次添加全部文件
     ```

   - 提交事务  可以多次add一次commit     

     ```powershell
     $ git commit -m "anything"			#双引号内输入本次提交的注释 重要！必写！
     ```

5. 查看状态

   - ```powershell
     $ git status
     On branch master
     Changes not staged for commit:
       (use "git add <file>..." to update what will be committed)
       (use "git checkout -- <file>..." to discard changes in working directory)
     
     	modified:   readme.md
     
     no changes added to commit (use "git add" and/or "git commit -a")
     ```

   - 可以查看当前仓库的状态，未修改，已修改未添加，已修改未提交

   - 查看具体修改内容

     ```powershell
     $ git diff readme.md
     diff --git a/readme.md b/readme.md
     index 46d49bf..9247db6 100644
     --- a/readme.md
     +++ b/readme.md
     @@ -1,2 +1,2 @@
     -Git is a version control system.
     +Git is a distributed version control system.
      Git is free software.
     ```

   - 查看日志

     ```powershell
     $ git log
     commit 1094adb7b9b3807259d8cb349e7df1d4d6477073 (HEAD -> master)
     Author: Michael Liao <askxuefeng@gmail.com>
     Date:   Fri May 18 21:06:15 2018 +0800
     
         append GPL
     
     commit e475afc93c209a690c39c13a46716e8fa000c366
     Author: Michael Liao <askxuefeng@gmail.com>
     Date:   Fri May 18 21:03:36 2018 +0800
     
         add distributed
     
     commit eaadf4e385e865d25c48e7ca9c8395c3f7dfaef0
     Author: Michael Liao <askxuefeng@gmail.com>
     Date:   Fri May 18 20:59:18 2018 +0800
     
         wrote a readme file
     ```

   - 查看简要日志

     ```powershell
     $ git log --pretty=oneline			#每条日志在一行内表示
     1094adb7b9b3807259d8cb349e7df1d4d6477073 (HEAD -> master) append GPL
     e475afc93c209a690c39c13a46716e8fa000c366 add distributed
     eaadf4e385e865d25c48e7ca9c8395c3f7dfaef0 wrote a readme file
     ```

   - **Git命令行下,显示内容太长时 按q可推出**

## 版本控制

1. 版本回退		HEAD表示当前版本

   - 回退到上个版本	

     ```powershell
     $ git reset --hard HEAD^
     HEAD is now at e475afc add distributed
     ```

   - 回退到前100个版本 

     ```powershell
     $ git reset --hard HEAD~100
     ```

   - 更改到指定版本   需要版本号支持

     ```powershell
     $ git reset --hard 1094a				#版本号无需写全，写前4~5位左右最好
     HEAD is now at 83b0afe append GPL
     ```

2. 查看每次命令的日志  如果找不到已经回滚的版本号，可用此命令

   ```powershell
   $ git reflog
   e475afc HEAD@{1}: reset: moving to HEAD^
   1094adb (HEAD -> master) HEAD@{2}: commit: append GPL
   e475afc HEAD@{3}: commit: add distributed
   eaadf4e HEAD@{4}: commit (initial): wrote a readme file
   #此处的版本号是操作过的每次命令，也可以用于回滚或者前进
   ```

3. 查看工作区和版本库里面最新版本的区别

   - ```powershell
     $ git diff HEAD -- readme.txt 
     diff --git a/readme.txt b/readme.txt
     index 76d770f..a9c5755 100644
     --- a/readme.txt
     +++ b/readme.txt
     @@ -1,4 +1,4 @@
      Git is a distributed version control system.
      Git is free software distributed under the GPL.
      Git has a mutable index called stage.
     -Git tracks changes.
     +Git tracks changes of files.
     ```

   - **文件每次修改都需要add后再commit才是最新版**

4. 撤销修改

   1. **文件写错，但并未add**

      - 手动把文件恢复到上一个版本的状态，之后查询工作区状态

      - ```powershell
        $ git status
        On branch master
        Changes not staged for commit:
          (use "git add <file>..." to update what will be committed)
          (use "git checkout -- <file>..." to discard changes in working directory)
        
        	modified:   readme.md
        
        no changes added to commit (use "git add" and/or "git commit -a")
        ```

      - 丢弃工作区的修改

        ```powershell
        $ git checkout -- readme.md
        #让这个文件回到最近一次git commit或git add时的状态
        ```

   2. 文件写错，已add，但并未commit

      - 查看状态

      - ```powershell
        $ git status
        On branch master
        Changes to be committed:
          (use "git reset HEAD <file>..." to unstage)
        
        	modified:   readme.md
        ```

      - 把暂存区的修改撤销掉（unstage）重新放回工作区

      - ```powershell
        $ git reset HEAD readme.txt
        Unstaged changes after reset:
        M	readme.txt
        ```

      - 再次查看状态    此时恢复到已修改未add状态

      - ```powershell
        $ git status
        On branch master
        Changes not staged for commit:
          (use "git add <file>..." to update what will be committed)
          (use "git checkout -- <file>..." to discard changes in working directory)
        
        	modified:   readme.md
        ```

      - 执行4.1中丢弃工作区的修改

      - ```powershell
        $ git checkout -- readme.txt
        
        $ git status
        On branch master
        nothing to commit, working tree clean
        ```

5. 删除文件

   1. 删除一个已commit的文件，通常直接rm

      - ```powershell
        $ rm test.txt
        ```

      - 之后查看工作区

      - ```powershell
        $ git status
        On branch master
        Changes not staged for commit:
          (use "git add/rm <file>..." to update what will be committed)
          (use "git checkout -- <file>..." to discard changes in working directory)
        
        	deleted:    test.txt
        
        no changes added to commit (use "git add" and/or "git commit -a")
        ```

      - A）从版本中删除该文件 git rm 后commit

      - ```powershell
        $ git rm test.txt
        rm 'test.txt'
        
        $ git commit -m "remove test.txt"
        [master d46f35e] remove test.txt
         1 file changed, 1 deletion(-)
         delete mode 100644 test.txt
        ```

      - B）把误删的文件恢复到最新版本

      - ```powershell
        $ git checkout -- test.txt
        ```

      - **git checkout其实是用版本库里的版本替换工作区的版本，无论工作区是修改还是删除，都可以“一键还原”。**

## 远程仓库

1. 将仓库更新至Github
   - git push -u github master					#远程库是空时，第一次push该仓库 需要加-u
   - git push github master                        #后面不用加

