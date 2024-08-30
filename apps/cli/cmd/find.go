/*
Copyright © 2024 NAME HERE <EMAIL ADDRESS>
*/
package cmd

import (
	"encoding/json"
	"fmt"
	"github.com/manifoldco/promptui"
	"github.com/spf13/cobra"
	"io/ioutil"
	"net/http"
	"os"
)

var (
	red     = "\033[31m"
	green   = "\033[32m"
	yellow  = "\033[33m"
	blue    = "\033[34m"
	magenta = "\033[35m"
	cyan    = "\033[36m"
	reset   = "\033[0m"
)

type Snippet struct {
	ID      int    `json:"id"`
	Title   string `json:"title"`
	Content string `json:"content"`
	Tags    string `json:"tags"`
}

// findCmd represents the find command
var findCmd = &cobra.Command{
	Use:   "find",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
		and usage of using your command. For example:
		
		Cobra is a CLI library for Go that empowers applications.
		This application is a tool to generate the needed files
		to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		version := "0.1 beta"
		fmt.Printf("%sSnippetShare Version %s %s\n", blue, version, reset)

		// Prompt the user for a search term
		searchPrompt := promptui.Prompt{
			Label: "🔎 Search for snippets",
			Templates: &promptui.PromptTemplates{
				Prompt:          "",
				Confirm:         "",
				Valid:           "",
				Invalid:         "",
				Success:         "",
				ValidationError: "",
				FuncMap:         nil,
			},
		}

		searchPromptResult, err := searchPrompt.Run()
		if err != nil {
			fmt.Printf("Prompt failed %v\n", err)
			return
		}

		// Make the HTTP request to get snippets
		serverPort := 8080
		requestURL := fmt.Sprintf("http://localhost:%d/snippet?tags=%s", serverPort, searchPromptResult)
		res, err := http.Get(requestURL)
		if err != nil {
			fmt.Printf("Error making HTTP request: %s\n", err)
			os.Exit(1)
		}
		defer res.Body.Close()

		body, err := ioutil.ReadAll(res.Body)
		if err != nil {
			fmt.Printf("Error reading body: %s\n", err)
			os.Exit(1)
		}

		var snippets []Snippet
		err = json.Unmarshal(body, &snippets)
		if err != nil {
			fmt.Printf("Error parsing JSON: %s\n", err)
			os.Exit(1)
		}

		if len(snippets) == 0 {
			fmt.Println("No snippets found for your search query.")
			return
		}

		var items []string
		for _, snippet := range snippets {
			if snippet.Title != "" {
				items = append(items, snippet.Title)
			} else {
				items = append(items, snippet.Content)
			}
		}

		// Prompt the user to select an item from the search results
		selectPrompt := promptui.Select{
			Label: "Select a snippet",
			Items: items,
		}

		_, result, err := selectPrompt.Run()
		if err != nil {
			fmt.Printf("Prompt failed %v\n", err)
			return
		}

		// Display the selected snippet content
		var selectedSnippet Snippet
		for _, snippet := range snippets {
			if snippet.Title == result || snippet.Content == result {
				selectedSnippet = snippet
				break
			}
		}

		// Final output
		fmt.Printf("%s Your Code (Copy & Paste):\n%s %s\n", green, selectedSnippet.Content, reset)

	},
}

func init() {
	rootCmd.AddCommand(findCmd)
}
