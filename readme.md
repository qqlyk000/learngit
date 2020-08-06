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

   - 创建空目录:horse:

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

6. Git命令行下,显示内容太长时 按q可推出

