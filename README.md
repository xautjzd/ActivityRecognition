ActivityRecognition
---

## 简介
行为识别方法库，将训练好的模型保存，现包含的识别方法有决策树与支持向量机。

1. 决策树

利用Hibernate从数据库读取提取好的特征训练集。训练好的模型保存在decisiontree_model.txt中


2. 支持向量机

将特征训练集与待分类测试集分别保存在svm_train_set与svm_test_set文件中。训练好的模型保存在svm_train_set.model中。
