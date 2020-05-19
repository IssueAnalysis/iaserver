import os
import numpy as np
from collections import Counter
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import confusion_matrix, precision_score, recall_score
from sklearn.svm import LinearSVC
from bert_serving.client import BertClient
import pandas as pd


    
def extract_features(data_path): 
    bc = BertClient()

    csv = pd.read_csv(data_path, header=-1)
    sentences = csv.iloc[:,0].tolist()
    labels = csv.iloc[:,1].tolist()

    features_matrix = bc.encode(sentences)

    print('encoding finished!')
    return features_matrix, labels
    

data_path = 'data1.csv'

#Prepare feature vectors per training mail and its labels

features_matrix,labels = extract_features(data_path)


#print(features_matrix.shape)
#print(labels.shape)
#print(sum(labels==0),sum(labels==1))


X_train, X_test, y_train, y_test = train_test_split(features_matrix, labels, test_size=0.40)

## Training models and its variants

model = LinearSVC()

model.fit(X_train,y_train)

result = model.predict(X_test)

print(confusion_matrix(y_test, result))
print("precision: ", precision_score(y_test, result, average=None))
print("recall: ", recall_score(y_test, result, average=None))

