#################################################################
# Input from user is taken. It is cleaned using NLP,            #
# and then it is send in the trained Neural Network model.      #
# Model predicts the tag of that sentence and then a random     #
# response corresponding to that tag is returned to user        #
#################################################################
import random
import json
import pickle
import numpy as np

import nltk
from nltk.stem import WordNetLemmatizer

from tensorflow import keras
from keras.layers import Dense
from keras.models import Sequential, load_model

#
from flask import Flask, jsonify, request
app = Flask(__name__)

## from tensorflow.keras.models import load_model

lemmatizer = WordNetLemmatizer()
intents = json.loads (open('Categories.json').read())

words = pickle.load (open('words.pkl', 'rb'))
classes = pickle.load (open('classes.pkl', 'rb'))
model = load_model ('chatbot.h5')

def cleanUpSentence (sentence):
    sentenceWords = nltk.word_tokenize (sentence)
    sentenceWords = [lemmatizer.lemmatize(word) for word in sentenceWords]
    return  sentenceWords


def bagOfWords (sentence):
    sentenceWords = cleanUpSentence (sentence)
    bag = [0] * len (words)
    for w in sentenceWords:
        for i, word in enumerate(words):
            if w == word :
                bag [i] = 1
    return np.array (bag)


def predictClass (sentence):
    bow = bagOfWords (sentence)
    res = model.predict (np.array([bow]))[0]     # 0.2 0.3 0.4 0 1 0000

    ERROR_TRESHOLD = 0.1
    results = [[i, r] for i, r in enumerate(res) if r > ERROR_TRESHOLD]

    results.sort (key = lambda x : x[1], reverse = True)
    returnList = []
    #print('results')
    #print(results)
    #print('Showing loop')
    
    for r in results:
     #   print(classes[r[0]])
      #  print(str(r[1]))
        returnList.append ({'intent' : classes[r[0]], 'probablity' : str(r[1])})
   
    #print('List')
    #print(returnList)
    res = getResponse(returnList,intents)
    return res



def getResponse (intentsList, intentsJson):
    tag = intentsList[0]['intent']
    listOfIntents = intentsJson['categories']
    for i in listOfIntents:
        if i['tag'] == tag:
            result = random.choice (i['responses'])
            url = random.choice (i['url'])
            break
    return result,url;


def chatWithBot():
    print("Start talking to Laura : ")
    while (True):
        message = input ("You : ")
        ints = predictClass (message)
    #res = getResponse (ints, intents)
        print ('Laura : ', ints)

#print ('Bot is Running !!')
####################
#@app.route('/chat/<msg>', methods=['GET','POST'])
#def chatWithBot(msg):
	#chatInput = request.form['chatInput']
#	return msg #jsonify(chatBotReply=predictClass(msg))


@app.route('/chat/<msg>', methods=['GET','POST'])
def chatWithBot(msg):
	#chatInput = request.form['chatInput']
    #url="<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/2Hpz1pWbQlE?si=c2PgMJz1P3NnZdvw\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"
    #return jsonify(chatBotReply=predictClass(msg), videoUrl= url )
    cbReply, url = predictClass(msg)
    
    print(url)
    return jsonify(chatBotReply= cbReply, videoUrl= url)
	
if __name__ == '__main__':
	app.run(host="192.168.66.76", debug=True)
     
# mahdi